package com.ptithcm.shopthoitrangnam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.SupplyDetailDto;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;
import com.ptithcm.shopthoitrangnam.entity.Supplier;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;
import com.ptithcm.shopthoitrangnam.mapper.SupplyDetailMapper;
import com.ptithcm.shopthoitrangnam.service.CostPriceService;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderService;
import com.ptithcm.shopthoitrangnam.service.SupplierService;
import com.ptithcm.shopthoitrangnam.service.SupplyDetailService;

import jakarta.validation.Valid;

@Controller
public class SupplyDetailController {
	@Autowired
	SupplyDetailService supplyDetailService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	CostPriceService costPriceService;
	
	@GetMapping(value = "/owner/supply-details", params = "supplier-code")
	public String supplyDetailPage(Model model, @RequestParam("supplier-code") String supplierCode, RedirectAttributes redirectAttributes) {
		List<SupplyDetail> supplyDetails = supplyDetailService.findAll().stream().filter(supplyDetail -> supplyDetail.getSupplyDetailPK().getSupplierCode().equals(supplierCode)).toList();
		model.addAttribute("supplyDetails", supplyDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("supplierCode", supplierCode);
		Boolean isDeletedSupplyDetail = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedSupplyDetail");
		if (isDeletedSupplyDetail != null) {
			model.addAttribute("isDeletedSupplyDetail", isDeletedSupplyDetail);
		}
		return "owner-supply-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/supply-details", params = {"supplier-code", "search"})
	public String searchSupplyDetail(Model model, @RequestParam("supplier-code") String supplierCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/supply-details?supplier-code=" + supplierCode;
		}
		
		List<SupplyDetail> supplyDetails = new ArrayList<>();
		if (NumberUtils.isCreatable(search)) {
			Optional<SupplyDetail> supplyDetail = supplyDetailService.findBySupplyDetailId(NumberUtils.createInteger(search));
			if (supplyDetail.isPresent() && supplyDetail.get().getSupplier().getSupplierCode().equals(supplierCode)) {
				supplyDetails.add(supplyDetail.get());
			}
		}
		 
		model.addAttribute("supplyDetails", supplyDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("supplierCode", supplierCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-supply-detail-manage.html";
	}
	
	@PostMapping(value = "/owner/supply-details", params = {"supplier-code", "create-page"})
	public String createSupplyDetailPage(Model model, @RequestParam("supplier-code") String supplierCode) {
		Supplier supplier = supplierService.findBySupplierCode(supplierCode).get();
		List<ProductDetail> productDetails = productDetailService.findAll().stream().filter(productDetail -> supplyDetailService.findBySupplierAndProductDetail(supplier, productDetail).isEmpty()).toList();
		SupplyDetailDto supplyDetailDto = new SupplyDetailDto();
		supplyDetailDto.setSupplierCode(supplierCode);
		model.addAttribute("productDetails", productDetails);
		model.addAttribute("supplyDetailDto", supplyDetailDto);
		return "owner-create-supply-detail.html";
	}
	
	@PostMapping(value = "/owner/supply-details", params = "create")
	public String createSupplyDetail(@Valid @ModelAttribute(name = "supplyDetailDto") SupplyDetailDto supplyDetailDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-supply-detail.html";
		}
		
		model.addAttribute("hasError", false);
		supplyDetailService.insert(supplyDetailDto);
		Supplier supplier = supplierService.findBySupplierCode(supplyDetailDto.getSupplierCode()).get();
		List<ProductDetail> productDetails = productDetailService.findAll().stream().filter(productDetail -> supplyDetailService.findBySupplierAndProductDetail(supplier, productDetail).isEmpty()).toList();
		model.addAttribute("productDetails", productDetails);
		return "owner-create-supply-detail.html";
	}
	
	@PostMapping(value = "/owner/supply-details/{supplyDetailId}", params = {"supplier-code", "delete"})
	public String deleteSupplyDetail(@PathVariable(name = "supplyDetailId") Integer supplyDetailId, 
			@RequestParam("supplier-code") String supplierCode, RedirectAttributes redirectAttributes, Model model) {
		SupplyDetail supplyDetail = supplyDetailService.findBySupplyDetailId(supplyDetailId).get();
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findBySupplier(supplyDetail.getSupplier());
		List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			purchaseOrderDetails.addAll(purchaseOrder.getPurchaseOrderDetails());
		}
		purchaseOrderDetails = purchaseOrderDetails.stream().filter(purchaseOrderDetail -> purchaseOrderDetail.getProductDetail().getProductDetailId() == supplyDetail.getProductDetail().getProductDetailId()).toList();
		if (!purchaseOrderDetails.isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedSupplyDetail", false);
			return "redirect:/owner/supply-details?supplier-code=" + supplierCode; 
		}
		
		costPriceService.deleteBySupplyDetail(supplyDetail);
		supplyDetailService.deleteBySupplyDetailId(supplyDetailId);
		redirectAttributes.addFlashAttribute("isDeletedSupplyDetail", true);
		model.addAttribute("supplierCode", supplierCode);
		return "redirect:/owner/supply-details?supplier-code=" + supplierCode;
	}
	
	@GetMapping(value = "/owner/supply-details", params = {"supplier-code", "page-number"})
	public String supplierPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("supplier-code") String supplierCode, Model model) {
		List<SupplyDetail> supplyDetails = supplyDetailService.findAll().stream().filter(supplyDetail -> supplyDetail.getSupplier().getSupplierCode().equals(supplierCode)).toList();
		model.addAttribute("supplyDetails", supplyDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("supplierCode", supplierCode);
		return "owner-supply-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/supply-details", params = {"supplier-code", "export"})
	@ResponseBody
	public List<SupplyDetailDto> getSupplierDtos(@RequestParam("supplier-code") String supplierCode) {
		List<SupplyDetailDto> supplyDetailDtos = SupplyDetailMapper.toSupplyDetailDtos(supplyDetailService.findAll().stream().filter(supplyDetail -> supplyDetail.getSupplyDetailPK().getSupplierCode().equals(supplierCode)).toList());
		return supplyDetailDtos;
	}
}

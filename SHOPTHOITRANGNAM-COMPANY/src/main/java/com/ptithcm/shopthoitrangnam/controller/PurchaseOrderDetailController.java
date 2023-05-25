package com.ptithcm.shopthoitrangnam.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDetailDto;
import com.ptithcm.shopthoitrangnam.entity.CostPrice;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;
import com.ptithcm.shopthoitrangnam.mapper.PurchaseOrderDetailMapper;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderDetailService;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderService;

import jakarta.validation.Valid;

@Controller
public class PurchaseOrderDetailController {
	@Autowired
	PurchaseOrderDetailService purchaseOrderDetailService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@GetMapping(value = "/owner/purchase-order-details", params = "purchase-order-code")
	public String purchaseOrderDetailPage(Model model, @RequestParam("purchase-order-code") String purchaseOrderCode, RedirectAttributes redirectAttributes) {
		List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService.findAll().stream().filter(purchaseOrderDetail -> purchaseOrderDetail.getPurchaseOrder().getPurchaseOrderCode().equals(purchaseOrderCode)).toList();
		model.addAttribute("purchaseOrderDetails", purchaseOrderDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("purchaseOrderCode", purchaseOrderCode);
		Boolean isDeletedPurchaseOrderDetail = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedPurchaseOrderDetail");
		if (isDeletedPurchaseOrderDetail != null) {
			model.addAttribute("isDeletedPurchaseOrderDetail", isDeletedPurchaseOrderDetail);
		}
		
		List<CostPrice> costPrices = new ArrayList<>();
		purchaseOrderDetails.forEach(purchaseOrderDetail -> {
			CostPrice costPrice = purchaseOrderDetail.getPurchaseOrder().getSupplier().getSupplyDetails().stream()
					.filter(supplyDetail -> supplyDetail.getProductDetail().getProductDetailId() == purchaseOrderDetail.getProductDetail().getProductDetailId()).findFirst().get()
					.getCostPrices().stream().filter(costPrice_ -> costPrice_.getEffectiveTime().getTime() <= new Date().getTime()).sorted(Comparator.comparing(CostPrice::getEffectiveTime).reversed()).findFirst().get();
			costPrices.add(costPrice);
		});
		model.addAttribute("costPrices", costPrices);
		
		List<BigDecimal> totalPrices = new ArrayList<>();
		for (int i = 0; i < purchaseOrderDetails.size(); ++i) {
			totalPrices.add(costPrices.get(i).getPrice().multiply(new BigDecimal(purchaseOrderDetails.get(i).getQuantity())));
		}
		model.addAttribute("totalPrices", totalPrices);
		
		return "owner-purchase-order-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/purchase-order-details", params = {"purchase-order-code", "search"})
	public String searchPurchaseOrderDetail(Model model, @RequestParam("purchase-order-code") String purchaseOrderCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/purchase-order-details?purchase-order-code=" + purchaseOrderCode;
		}
		
		Set<PurchaseOrderDetail> purchaseOrderDetails = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			Optional<PurchaseOrderDetail> purchaseOrderDetail = purchaseOrderDetailService.findByPurchaseOrderAndProductDetail(purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get(), productDetailService.findByProductDetailId(NumberUtils.createInteger(search)).get());
			if (purchaseOrderDetail.isPresent()) {
				purchaseOrderDetails.add(purchaseOrderDetail.get());
			}
		}
		String pattern = ".*" + search.replaceAll(" ", "\\\\*") + ".*";
		List<PurchaseOrderDetail> purchaseOrderDetailsByProductName = purchaseOrderDetailService.findAll().stream().filter(purchaseOrderDetail -> purchaseOrderDetail.getProductDetail().getProduct().getProductName().matches(pattern)).toList();
		purchaseOrderDetails.addAll(purchaseOrderDetailsByProductName);
		 
		model.addAttribute("purchaseOrderDetails", purchaseOrderDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("purchaseOrderCode", purchaseOrderCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		List<CostPrice> costPrices = new ArrayList<>();
		purchaseOrderDetails.forEach(purchaseOrderDetail -> {
			CostPrice costPrice = purchaseOrderDetail.getPurchaseOrder().getSupplier().getSupplyDetails().stream()
					.filter(supplyDetail -> supplyDetail.getProductDetail().getProductDetailId() == purchaseOrderDetail.getProductDetail().getProductDetailId()).findFirst().get()
					.getCostPrices().stream().filter(costPrice_ -> costPrice_.getEffectiveTime().getTime() <= new Date().getTime()).sorted(Comparator.comparing(CostPrice::getEffectiveTime).reversed()).findFirst().get();
			costPrices.add(costPrice);
		});
		model.addAttribute("costPrices", costPrices);
		
		List<BigDecimal> totalPrices = new ArrayList<>();
		for (int i = 0; i < purchaseOrderDetails.size(); ++i) {
			totalPrices.add(costPrices.get(i).getPrice().multiply(new BigDecimal(((PurchaseOrderDetail) purchaseOrderDetails.toArray()[i]).getQuantity())));
		}
		model.addAttribute("totalPrices", totalPrices);
		
		return "owner-purchase-order-detail-manage.html";
	}
	
	@PostMapping(value = "/owner/purchase-order-details", params = {"purchase-order-code", "create-page"})
	public String createPurchaseOrderDetailPage(Model model, @RequestParam("purchase-order-code") String purchaseOrderCode) {
		PurchaseOrderDetailDto purchaseOrderDetailDto = new PurchaseOrderDetailDto();
		purchaseOrderDetailDto.setPurchaseOrderCode(purchaseOrderCode);
		model.addAttribute("purchaseOrderDetailDto", purchaseOrderDetailDto);
		
		List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService.findByPurchaseOrder(purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get());
		List<ProductDetail> productDetails = purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get().getSupplier().getSupplyDetails()
				.stream().map(supplyDetail -> supplyDetail.getProductDetail())
				.filter(productDetail -> purchaseOrderDetails.stream().noneMatch(purchaseOrderDetail -> purchaseOrderDetail.getPurchaseOrderDetailPK().getProductDetailId() != productDetail.getProductDetailId()))
				.toList();
		model.addAttribute("productDetails", productDetails);
		return "owner-create-purchase-order-detail.html";
	}
	
	@PostMapping(value = "/owner/purchase-order-details", params = "create")
	public String createPurchaseOrderDetail(@Valid @ModelAttribute(name = "purchaseOrderDetailDto") PurchaseOrderDetailDto purchaseOrderDetailDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-purchase-order-detail.html";
		}
		
		model.addAttribute("hasError", false);
		purchaseOrderDetailService.save(purchaseOrderDetailDto);
		return "owner-create-purchase-order-detail.html";
	}
	
	@PostMapping(value = "/owner/purchase-order-details/{purchaseOrderCode}/{productDetailId}", params = "update-page")
	public String updateProductDetailPage(Model model, @PathVariable(name = "purchaseOrderCode") String purchaseOrderCode,  @PathVariable(name = "productDetailId") Integer productDetailId) {
		PurchaseOrderDetailDto purchaseOrderDetailDto = PurchaseOrderDetailMapper.toPurchaseOrderDetailDto(purchaseOrderDetailService.findByPurchaseOrderAndProductDetail(purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get(), productDetailService.findByProductDetailId(productDetailId).get()).get());
		model.addAttribute("purchaseOrderDetailDto", purchaseOrderDetailDto);
		return "owner-update-purchase-order-detail.html";
	}
	
	@PostMapping(value = "/owner/purchase-order-details/{purchaseOrderCode}/{productDetailId}", params = "update")
	public String updatePurchaseOrderDetail(@PathVariable(name = "purchaseOrderCode") String purchaseOrderCode,  @Valid @ModelAttribute(name = "purchaseOrderDetailDto") PurchaseOrderDetailDto purchaseOrderDetailDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-purchase-order-detail.html";
		}
		
		purchaseOrderDetailService.update(purchaseOrderDetailDto);
		model.addAttribute("hasError", false);
		return "owner-update-purchase-order-detail.html";
	}
	
	@PostMapping(value = "/owner/purchase-order-details/{purchaseOrderCode}/{productDetailId}", params = "delete")
	public String deletePurchaseOrderDetail(@PathVariable(name = "purchaseOrderCode") String purchaseOrderCode, 
			@PathVariable("productDetailId") Integer productDetailId, RedirectAttributes redirectAttributes, Model model) {
		PurchaseOrder purchaseOrder = purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get();
		if (purchaseOrder.getStatus() != null) {
			redirectAttributes.addFlashAttribute("isDeletedPurchaseOrderDetail", false);
			return "redirect:/owner/purchase-order-details?purchase-order-code=" + purchaseOrderCode; 
		}
		
		purchaseOrderDetailService.deleteByPurchaseOrderAndProductDetail(purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get(), productDetailService.findByProductDetailId(productDetailId).get());
		redirectAttributes.addFlashAttribute("isDeletedPurchaseOrderDetail", true);
		model.addAttribute("purchaseOrderCode", purchaseOrderCode);
		return "redirect:/owner/purchase-order-details?purchase-order-code=" + purchaseOrderCode;
	}
	
	@GetMapping(value = "/owner/purchase-order-details", params = {"purchase-order-code", "page-number"})
	public String purchaseOrderDetailPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("purchase-order-code") String purchaseOrderCode, Model model) {
		List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService.findAll().stream().filter(purchaseOrderDetail -> purchaseOrderDetail.getPurchaseOrder().getPurchaseOrderCode().equals(purchaseOrderCode)).toList();
		model.addAttribute("purchaseOrderDetails", purchaseOrderDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("purchaseOrderCode", purchaseOrderCode);
		
		List<CostPrice> costPrices = new ArrayList<>();
		purchaseOrderDetails.forEach(purchaseOrderDetail -> {
			CostPrice costPrice = purchaseOrderDetail.getPurchaseOrder().getSupplier().getSupplyDetails().stream()
					.filter(supplyDetail -> supplyDetail.getProductDetail().getProductDetailId() == purchaseOrderDetail.getProductDetail().getProductDetailId()).findFirst().get()
					.getCostPrices().stream().filter(costPrice_ -> costPrice_.getEffectiveTime().getTime() <= new Date().getTime()).sorted(Comparator.comparing(CostPrice::getEffectiveTime).reversed()).findFirst().get();
			costPrices.add(costPrice);
		});
		model.addAttribute("costPrices", costPrices);
		
		List<BigDecimal> totalPrices = new ArrayList<>();
		for (int i = 0; i < purchaseOrderDetails.size(); ++i) {
			totalPrices.add(costPrices.get(i).getPrice().multiply(new BigDecimal(purchaseOrderDetails.get(i).getQuantity())));
		}
		model.addAttribute("totalPrices", totalPrices);
		
		return "owner-purchase-order-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/purchase-order-details", params = {"purchase-order-code", "export"})
	@ResponseBody
	public List<PurchaseOrderDetailDto> getPurchaseOrderDetailDtos(@RequestParam("purchase-order-code") String purchaseOrderCode) {
		List<PurchaseOrderDetailDto> purchaseOrderDetailDtos = PurchaseOrderDetailMapper.toPurchaseOrderDetailDtos(purchaseOrderDetailService.findAll().stream().filter(purchaseOrderDetail -> purchaseOrderDetail.getPurchaseOrder().getPurchaseOrderCode().equals(purchaseOrderCode)).toList());
		return purchaseOrderDetailDtos;
	}
}

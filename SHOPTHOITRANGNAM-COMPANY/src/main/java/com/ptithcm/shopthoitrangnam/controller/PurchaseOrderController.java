package com.ptithcm.shopthoitrangnam.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.mapper.PurchaseOrderMapper;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderDetailService;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderService;

import jakarta.validation.Valid;

@Controller
public class PurchaseOrderController {
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	PurchaseOrderDetailService purchaseOrderDetailService;
	
	@Autowired
	@Qualifier("createPurchaseOrderFormValidator")
	Validator createPurchaseOrderFormValidator;
	
	@GetMapping(value = "/owner/purchase-orders", params = "supplier-code")
	public String purchaseOrderPage(Model model, @RequestParam("supplier-code") String supplierCode, RedirectAttributes redirectAttributes) {
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findAll().stream().filter(purchaseOrder -> purchaseOrder.getSupplier().getSupplierCode().equals(supplierCode)).toList();
		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("supplierCode", supplierCode);
		Boolean isDeletedPurchaseOrder = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedPurchaseOrder");
		if (isDeletedPurchaseOrder != null) {
			model.addAttribute("isDeletedPurchaseOrder", isDeletedPurchaseOrder);
		}
		return "owner-purchase-order-manage.html";
	}
	
	@GetMapping(value = "/owner/purchase-orders", params = {"supplier-code", "search"})
	public String searchPurchaseOrder(Model model, @RequestParam("supplier-code") String supplierCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/purchase-orders?supplier-code=" + supplierCode;
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<PurchaseOrder> purchaseOrdersByCode = purchaseOrderService.findByPurchaseOrderCodeUsingRegex(pattern);
		List<PurchaseOrder> purchaseOrdersByName = purchaseOrderService.findByPurchaseOrderNameUsingRegex(pattern);
		Set<PurchaseOrder> purchaseOrders = new LinkedHashSet<>(purchaseOrdersByCode);
		purchaseOrders.addAll(purchaseOrdersByName);
		 
		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("isSearched", true);
		model.addAttribute("supplierCode", supplierCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-purchase-order-manage.html";
	}
	
	@PostMapping(value = "/owner/purchase-orders", params = {"supplier-code", "create-page"})
	public String createPurchaseOrderPage(Model model, @RequestParam("supplier-code") String supplierCode) {
		PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
		purchaseOrderDto.setSupplierCode(supplierCode);
		model.addAttribute("purchaseOrderDto", purchaseOrderDto);
		return "owner-create-purchase-order.html";
	}
	
	@PostMapping(value = "/owner/purchase-orders", params = "create")
	public String createPurchaseOrder(@Valid @ModelAttribute(name = "purchaseOrderDto") PurchaseOrderDto purchaseOrderDto, BindingResult bindingResult, Model model) {
		createPurchaseOrderFormValidator.validate(purchaseOrderDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-purchase-order.html";
		}
		
		model.addAttribute("hasError", false);
		purchaseOrderService.save(purchaseOrderDto);
		return "owner-create-purchase-order.html";
	}
	
	@PostMapping(value = "/owner/purchase-orders/{purchaseOrderCode}", params = "update-page")
	public String updateProductDetailPage(Model model, @PathVariable(name = "purchaseOrderCode") String purchaseOrderCode) {
		PurchaseOrderDto purchaseOrderDto = PurchaseOrderMapper.toPurchaseOrderDto(purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get());
		model.addAttribute("purchaseOrderDto", purchaseOrderDto);
		return "owner-update-purchase-order.html";
	}
	
	@PostMapping(value = "/owner/purchase-orders/{purchaseOrderCode}", params = "update")
	public String updateProductDetail(@Valid @ModelAttribute(name = "purchaseOrderDto") PurchaseOrderDto purchaseOrderDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-purchase-order.html";
		}
		
		purchaseOrderService.save(purchaseOrderDto);
		model.addAttribute("hasError", false);
		return "owner-update-purchase-order.html";
	}
	
	@PostMapping(value = "/owner/purchase-orders/{purchaseOrderCode}", params = {"supplier-code", "delete"})
	public String deletePurchaseOrder(@PathVariable(name = "purchaseOrderCode") String purchaseOrderCode, 
			@RequestParam("supplier-code") String supplierCode, RedirectAttributes redirectAttributes, Model model) {
		PurchaseOrder purchaseOrder = purchaseOrderService.findByPurchaseOrderCode(purchaseOrderCode).get();
		if (purchaseOrder.getStatus()) {
			redirectAttributes.addFlashAttribute("isDeletedPurchaseOrder", false);
			return "redirect:/owner/purchase-orders?supplier-code=" + supplierCode; 
		}
		
		purchaseOrderDetailService.deleteByPurchaseOrder(purchaseOrder);
		purchaseOrderService.deleteByPurchaseOrderCode(purchaseOrderCode);
		redirectAttributes.addFlashAttribute("isDeletedPurchaseOrder", true);
		model.addAttribute("supplierCode", supplierCode);
		return "redirect:/owner/purchase-orders?supplier-code=" + supplierCode;
	}
	
	@GetMapping(value = "/owner/purchase-orders", params = {"supplier-code", "page-number"})
	public String purchaseOrderPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("supplier-code") String supplierCode, Model model) {
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findAll().stream().filter(purchaseOrder -> purchaseOrder.getSupplier().getSupplierCode().equals(supplierCode)).toList();
		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("supplierCode", supplierCode);
		return "owner-purchase-order-manage.html";
	}
	
	@GetMapping(value = "/owner/purchase-orders", params = {"supplier-code", "export"})
	@ResponseBody
	public List<PurchaseOrderDto> getPurchaseOrderDtos(@RequestParam("supplier-code") String supplierCode) {
		List<PurchaseOrderDto> purchaseOrderDtos = PurchaseOrderMapper.toPurchaseOrderDtos(purchaseOrderService.findAll().stream().filter(purchaseOrder -> purchaseOrder.getSupplier().getSupplierCode().equals(supplierCode)).toList());
		return purchaseOrderDtos;
	}
}

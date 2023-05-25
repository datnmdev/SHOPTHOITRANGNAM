package com.ptithcm.shopthoitrangnam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
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

import com.ptithcm.shopthoitrangnam.dto.CostPriceDto;
import com.ptithcm.shopthoitrangnam.dto.SellingPriceDto;
import com.ptithcm.shopthoitrangnam.entity.CostPrice;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;
import com.ptithcm.shopthoitrangnam.mapper.CostPriceMapper;
import com.ptithcm.shopthoitrangnam.mapper.SellingPriceMapper;
import com.ptithcm.shopthoitrangnam.service.CostPriceService;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderDetailService;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderService;
import com.ptithcm.shopthoitrangnam.service.SellingPriceService;
import com.ptithcm.shopthoitrangnam.service.SupplyDetailService;

import jakarta.validation.Valid;

@Controller
public class CostPriceController {
	@Autowired
	CostPriceService costPriceService;
	
	@Autowired
	SupplyDetailService supplyDetailService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	@Qualifier("createCostPriceFormValidator")
	Validator createCostPriceFormValidator;
	
	@Autowired
	@Qualifier("updateCostPriceFormValidator")
	Validator updateCostPriceFormValidator;
	
	@GetMapping(value = "/owner/cost-prices", params = "supply-detail-id")
	public String costPricePage(Model model, @RequestParam("supply-detail-id") Integer supplyDetailId, RedirectAttributes redirectAttributes) {
		List<CostPrice> costPrices = costPriceService.findBySupplyDetail(supplyDetailService.findBySupplyDetailId(supplyDetailId).get());
		model.addAttribute("costPrices", costPrices);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("supplyDetailId", supplyDetailId);
		Boolean isDeletedCostPrice = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedCostPrice");
		if (isDeletedCostPrice != null) {
			model.addAttribute("isDeletedCostPrice", isDeletedCostPrice);
		}
		return "owner-cost-price-manage.html";
	}
	
	@GetMapping(value = "/owner/cost-prices", params = {"supply-detail-id", "search"})
	public String searchCostPrice(Model model, @RequestParam("supply-detail-id") Integer supplyDetailId, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/cost-prices?supply-detail-id=" + supplyDetailId;
		}
		
		List<CostPrice> costPrices = new ArrayList<>();
		if (NumberUtils.isCreatable(search)) {
			Optional<CostPrice> costPrice = costPriceService.findByCostPriceId(NumberUtils.createInteger(search));
			if (costPrice.isPresent() && costPrice.get().getSupplyDetail().getSupplyDetailId() == supplyDetailId) {
				costPrices.add(costPrice.get());
			}
		}
		 
		model.addAttribute("costPrices", costPrices);
		model.addAttribute("isSearched", true);
		model.addAttribute("supplyDetailId", supplyDetailId);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-cost-price-manage.html";
	}
	
	@PostMapping(value = "/owner/cost-prices", params = {"supply-detail-id", "create-page"})
	public String createCostPricePage(Model model, @RequestParam("supply-detail-id") Integer supplyDetailId) {
		CostPriceDto costPriceDto = new CostPriceDto();
		costPriceDto.setSupplyDetailId(supplyDetailId);
		model.addAttribute("costPriceDto", costPriceDto);
		return "owner-create-cost-price.html";
	}
	
	@PostMapping(value = "/owner/cost-prices", params = "create")
	public String createCostPrice(@Valid @ModelAttribute(name = "costPriceDto") CostPriceDto costPriceDto, BindingResult bindingResult, Model model) {
		createCostPriceFormValidator.validate(costPriceDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-cost-price.html";
		}
		
		model.addAttribute("hasError", false);
		costPriceService.save(costPriceDto);
		return "owner-create-cost-price.html";
	}
	
	@PostMapping(value = "/owner/cost-prices/{costPriceId}", params = "update-page")
	public String updateCostPricePage(Model model, @PathVariable(name = "costPriceId") Integer costPriceId) {
		CostPriceDto costPriceDto = CostPriceMapper.toCostPriceDto(costPriceService.findByCostPriceId(costPriceId).get());
		model.addAttribute("costPriceDto", costPriceDto);
		return "owner-update-cost-price.html";
	}
	
	@PostMapping(value = "/owner/cost-prices/{costPriceId}", params = "update")
	public String updateCostPrice(@Valid @ModelAttribute(name = "costPriceDto") CostPriceDto costPriceDto, BindingResult bindingResult, Model model) {
		updateCostPriceFormValidator.validate(costPriceDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-cost-price.html";
		}
		
		model.addAttribute("hasError", false);
		costPriceService.save(costPriceDto);
		return "owner-update-cost-price.html";
	}
	
	@PostMapping(value = "/owner/cost-prices/{costPriceId}", params = {"supply-detail-id", "delete"})
	public String deleteCostPrice(@PathVariable(name = "costPriceId") Integer costPriceId,
			@RequestParam("supply-detail-id") Integer supplyDetailId, RedirectAttributes redirectAttributes, Model model) {
		CostPrice costPrice = costPriceService.findByCostPriceId(costPriceId).get();
		SupplyDetail supplyDetail = supplyDetailService.findBySupplyDetailId(supplyDetailId).get();
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findBySupplier(supplyDetail.getSupplier());
		if (purchaseOrders.stream().anyMatch(purchaseOrder -> purchaseOrder.getCreationTime().getTime() >= costPrice.getEffectiveTime().getTime() && purchaseOrder.getPurchaseOrderDetails()
				.stream().anyMatch(purchaseOrderDetail -> purchaseOrderDetail.getProductDetail().getProductDetailId() == supplyDetail.getProductDetail().getProductDetailId()))) {
			redirectAttributes.addFlashAttribute("isDeletedSellingPrice", false);
			return "redirect:/owner/cost-prices?supply-detail-id=" + supplyDetailId; 
		}
		
		costPriceService.deleteByCostPriceId(costPriceId);
		redirectAttributes.addFlashAttribute("isDeletedCostPrice", true);
		model.addAttribute("supplyDetailId", supplyDetailId);
		return "redirect:/owner/cost-prices?supply-detail-id=" + supplyDetailId;
	}
	
	@GetMapping(value = "/owner/cost-prices", params = {"supply-detail-id", "page-number"})
	public String costPricePageNumber(@RequestParam("supply-detail-id") Integer supplyDetailId, @RequestParam("page-number") Integer pageNumber, Model model) {
		List<CostPrice> costPrices = costPriceService.findAll().stream().filter(costPrice -> costPrice.getSupplyDetail().getSupplyDetailId() == supplyDetailId).toList();
		model.addAttribute("costPrices", costPrices);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("supplyDetailId", supplyDetailId);
		return "owner-cost-price-manage.html";
	}
	
	@GetMapping(value = "/owner/cost-prices", params = {"supply-detail-id", "export"})
	@ResponseBody
	public List<CostPriceDto> getCostPriceDtos(@RequestParam("supply-detail-id") Integer supplyDetailId) {
		List<CostPriceDto> costPriceDtos = CostPriceMapper.toCostPriceDtos(costPriceService.findBySupplyDetail(supplyDetailService.findBySupplyDetailId(supplyDetailId).get()));
		return costPriceDtos;
	}
}

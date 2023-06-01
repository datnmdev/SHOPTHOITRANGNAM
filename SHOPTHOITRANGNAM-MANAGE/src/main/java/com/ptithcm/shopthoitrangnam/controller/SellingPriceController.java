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

import com.ptithcm.shopthoitrangnam.dto.SellingPriceDto;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.mapper.SellingPriceMapper;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.SellingPriceService;

import jakarta.validation.Valid;

@Controller
public class SellingPriceController {
	@Autowired
	SellingPriceService sellingPriceService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	@Qualifier("createSellingPriceFormValidator")
	Validator createSellingPriceFormValidator;
	
	@Autowired
	@Qualifier("updateSellingPriceFormValidator")
	Validator updateSellingPriceFormValidator;
	
	@GetMapping("/owner/selling-prices")
	public String sellingPricePage(Model model, RedirectAttributes redirectAttributes) {
		List<SellingPrice> sellingPrices = sellingPriceService.findAll();
		model.addAttribute("sellingPrices", sellingPrices);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedSellingPrice = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedSellingPrice");
		if (isDeletedSellingPrice != null) {
			model.addAttribute("isDeletedSellingPrice", isDeletedSellingPrice);
		}
		return "owner-selling-price-manage.html";
	}
	
	@GetMapping(value = "/owner/selling-prices", params = "search")
	public String searchSellingPrice(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/selling-prices";
		}
		
		List<SellingPrice> sellingPrices = new ArrayList<>();
		if (NumberUtils.isCreatable(search)) {
			Optional<SellingPrice> sellingPrice = sellingPriceService.findBySellingPriceId(NumberUtils.createInteger(search));
			if (sellingPrice.isPresent()) {
				sellingPrices.add(sellingPrice.get());
			}
		}
		
		model.addAttribute("sellingPrices", sellingPrices);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-selling-price-manage.html";
	}
	
	@GetMapping(value = "/owner/selling-prices", params = "product-detail-id")
	public String filterSellingPrice(Model model, @RequestParam("product-detail-id") Integer productDetailId) {
		List<SellingPrice> sellingPrices = sellingPriceService.findAll().stream().filter(sellingPrice -> sellingPrice.getProductDetail().getProductDetailId().equals(productDetailId)).toList();
		model.addAttribute("sellingPrices", sellingPrices);
		model.addAttribute("productDetailId", productDetailId);
		model.addAttribute("pageNumber", 0);
		return "owner-selling-price-manage.html";
	}
	
	@PostMapping(value = "/owner/selling-prices", params = {"product-detail-id", "create-page"})
	public String filterAndCreateSellingPrice(Model model, @RequestParam("product-detail-id") Integer productDetailId) {
		SellingPriceDto sellingPriceDto = new SellingPriceDto();
		sellingPriceDto.setProductDetailId(productDetailId);
		model.addAttribute("sellingPriceDto", sellingPriceDto);
		model.addAttribute("productDetailId", productDetailId);
		return "owner-create-selling-price.html";
	}
	
	@PostMapping(value = "/owner/selling-prices", params = "create-page")
	public String createSellingPricePage(Model model) {
		List<ProductDetail> productDetails = productDetailService.findAll();
		model.addAttribute("productDetails", productDetails);
		model.addAttribute("sellingPriceDto", new SellingPriceDto());
		return "owner-create-selling-price.html";
	}
	
	@PostMapping(value = "/owner/selling-prices", params = "create")
	public String createSellingPrice(@Valid @ModelAttribute(name = "sellingPriceDto") SellingPriceDto sellingPriceDto, BindingResult bindingResult, Model model) {
		List<ProductDetail> productDetails = productDetailService.findAll();
		model.addAttribute("productDetails", productDetails);
		
		createSellingPriceFormValidator.validate(sellingPriceDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-selling-price.html";
		}
		
		model.addAttribute("hasError", false);
		sellingPriceService.save(sellingPriceDto);
		return "owner-create-selling-price.html";
	}
	
	@PostMapping(value = "/owner/selling-prices/{sellingPriceId}", params = "update-page")
	public String updateSellingPricePage(Model model, @PathVariable(name = "sellingPriceId") Integer sellingPriceId) {
		SellingPriceDto sellingPriceDto = SellingPriceMapper.toSellingPriceDto(sellingPriceService.findBySellingPriceId(sellingPriceId).get());
		model.addAttribute("sellingPriceDto", sellingPriceDto);
		return "owner-update-selling-price.html";
	}
	
	@PostMapping(value = "/owner/selling-prices/{sellingPriceId}", params = "update")
	public String updateSellingPrice(@Valid @ModelAttribute(name = "sellingPriceDto") SellingPriceDto sellingPriceDto, BindingResult bindingResult, Model model) {
		updateSellingPriceFormValidator.validate(sellingPriceDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-selling-price.html";
		}
		
		model.addAttribute("hasError", false);
		sellingPriceService.save(sellingPriceDto);
		return "owner-update-selling-price.html";
	}
	
	@PostMapping(value = "/owner/selling-prices/{sellingPriceId}", params = "delete")
	public String deleteSize(@PathVariable(name = "sellingPriceId") Integer sellingPriceId, RedirectAttributes redirectAttributes) {
		if (!productDetailService.findByProductDetailId(sellingPriceService.findBySellingPriceId(sellingPriceId).get().getProductDetail().getProductDetailId()).get().getOrderDetails().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedSellingPrice", false);
			return "redirect:/owner/selling-prices"; 
		}
		
		sellingPriceService.deleteBySellingPriceId(sellingPriceId);
		redirectAttributes.addFlashAttribute("isDeletedSellingPrice", true);
		return "redirect:/owner/selling-prices";
	}
	
	@GetMapping(value = "/owner/selling-prices", params = "page-number")
	public String sellingPricePageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<SellingPrice> sellingPrices = sellingPriceService.findAll();
		model.addAttribute("sellingPrices", sellingPrices);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-selling-price-manage.html";
	}
	
	@GetMapping(value = "/owner/selling-prices", params = "export")
	@ResponseBody
	public List<SellingPriceDto> getSellingPriceDtos() {
		List<SellingPriceDto> sellingPriceDtos = SellingPriceMapper.toSellingPriceDtos(sellingPriceService.findAll());
		return sellingPriceDtos;
	}
}

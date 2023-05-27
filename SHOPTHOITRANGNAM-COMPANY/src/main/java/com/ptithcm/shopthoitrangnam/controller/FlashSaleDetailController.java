package com.ptithcm.shopthoitrangnam.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.FlashSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.mapper.FlashSaleDetailMapper;
import com.ptithcm.shopthoitrangnam.service.FlashSaleDetailService;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;

import jakarta.validation.Valid;

@Controller
public class FlashSaleDetailController {
	@Autowired
	FlashSaleDetailService flashSaleDetailService;
	
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	@Qualifier("createFlashSaleDetailFormValidator")
	Validator createFlashSaleDetailFormValidator;
	
	@Autowired
	@Qualifier("updateFlashSaleDetailFormValidator")
	Validator updateFlashSaleDetailFormValidator;
	
	@GetMapping(value = "/owner/flash-sale-details", params = "flash-sale-id")
	public String purchaseFlashSaleDetailPage(Model model, @RequestParam("flash-sale-id") Integer flashSaleId, RedirectAttributes redirectAttributes) {
		List<FlashSaleDetail> flashSaleDetails = flashSaleDetailService.findAll().stream().filter(flashSaleDetail -> flashSaleDetail.getFlashSaleDetailPK().getFlashSaleId().equals(flashSaleId)).toList();
		model.addAttribute("flashSaleDetails", flashSaleDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("flashSaleId", flashSaleId);
		Boolean isDeletedFlashSaleDetail = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedFlashSaleDetail");
		if (isDeletedFlashSaleDetail != null) {
			model.addAttribute("isDeletedFlashSaleDetail", isDeletedFlashSaleDetail);
		}
		
		List<BigDecimal> originalPrices = new ArrayList<>();
		List<BigDecimal> salePrices = new ArrayList<>();
		flashSaleDetails.forEach(flashSaleDetail -> {
			BigDecimal originalPrice_ = flashSaleDetail.getProductDetail().getSellingPrices().stream()
					.filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= new Date().getTime())
					.sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			originalPrices.add(originalPrice_);
			salePrices.add(originalPrice_.multiply(new BigDecimal(100 - flashSaleDetail.getFlashSalePercentage())).divide(new BigDecimal(100)));
		});
		model.addAttribute("salePrices", salePrices);
		
		Integer status;
		Date now = new Date();
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleId).get();
		if (flashSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flashSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-flash-sale-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/flash-sale-details", params = {"flash-sale-id", "search"})
	public String searchFlashSaleDetail(Model model, @RequestParam("flash-sale-id") Integer flashSaleId, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/flash-sale-details?flash-sale-id=" + flashSaleId;
		}
		
		search = search.trim();
		Set<FlashSaleDetail> flashSaleDetails = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			FlashSaleDetailPK flashSaleDetailPK = new FlashSaleDetailPK();
			flashSaleDetailPK.setFlashSaleId(flashSaleId);
			flashSaleDetailPK.setProductDetailId(NumberUtils.createInteger(search));
			Optional<FlashSaleDetail> flashSaleDetail = flashSaleDetailService.findByFlashSaleDetailPK(flashSaleDetailPK);
			if (flashSaleDetail.isPresent()) {
				flashSaleDetails.add(flashSaleDetail.get());
			}
		}
		
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<FlashSaleDetail> flashSaleDetailsByProductName = flashSaleDetailService.findAll().stream().filter(flashSaleDetail -> flashSaleDetail.getProductDetail().getProduct().getProductName().matches(pattern)).toList();
		flashSaleDetails.addAll(flashSaleDetailsByProductName);
		 
		model.addAttribute("flashSaleDetails", flashSaleDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("flashSaleId", flashSaleId);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		List<BigDecimal> originalPrices = new ArrayList<>();
		List<BigDecimal> salePrices = new ArrayList<>();
		flashSaleDetails.forEach(flashSaleDetail -> {
			BigDecimal originalPrice_ = flashSaleDetail.getProductDetail().getSellingPrices().stream()
					.filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= new Date().getTime())
					.sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			originalPrices.add(originalPrice_);
			salePrices.add(originalPrice_.multiply(new BigDecimal(100 - flashSaleDetail.getFlashSalePercentage())).divide(new BigDecimal(100)));
		});
		model.addAttribute("salePrices", salePrices);
		
		Integer status;
		Date now = new Date();
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleId).get();
		if (flashSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flashSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-flash-sale-detail-manage.html";
	}
	
	@PostMapping(value = "/owner/flash-sale-details", params = {"flash-sale-id", "create-page"})
	public String createFlashSaleDetailPage(Model model, @RequestParam("flash-sale-id") Integer flashSaleId) {
		FlashSaleDetailDto flashSaleDetailDto = new FlashSaleDetailDto();
		flashSaleDetailDto.setFlashSaleId(flashSaleId);
		model.addAttribute("flashSaleDetailDto", flashSaleDetailDto);
		
		List<ProductDetail> productDetailsInFlashSaleDetails = flashSaleDetailService.findByFlashSale(flashSaleService.findByFlashSaleId(flashSaleId).get()).stream().map(flashSaleDetail -> flashSaleDetail.getProductDetail()).toList();
		List<ProductDetail> productDetails = productDetailService.findAll();
		productDetails.removeAll(productDetailsInFlashSaleDetails);
		model.addAttribute("productDetails", productDetails);
		return "owner-create-flash-sale-detail.html";
	}
	
	@PostMapping(value = "/owner/flash-sale-details", params = "create")
	public String createFlashSaleDetail(@Valid @ModelAttribute(name = "flashSaleDetailDto") FlashSaleDetailDto flashSaleDetailDto, BindingResult bindingResult, Model model) {
		createFlashSaleDetailFormValidator.validate(flashSaleDetailDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-flash-sale-detail.html";
		}
		
		model.addAttribute("hasError", false);
		flashSaleDetailService.save(flashSaleDetailDto);
		
		List<ProductDetail> productDetailsInFlashSaleDetails = flashSaleDetailService.findByFlashSale(flashSaleService.findByFlashSaleId(flashSaleDetailDto.getFlashSaleId()).get()).stream().map(flashSaleDetail -> flashSaleDetail.getProductDetail()).toList();
		List<ProductDetail> productDetails = productDetailService.findAll();
		productDetails.removeAll(productDetailsInFlashSaleDetails);
		model.addAttribute("productDetails", productDetails);
		
		return "owner-create-flash-sale-detail.html";
	}
	
	@PostMapping(value = "/owner/flash-sale-details/{flashSaleId}/{productDetailId}", params = "update-page")
	public String updateFlashSaleDetailPage(Model model, @PathVariable(name = "flashSaleId") Integer flashSaleId, @PathVariable(name = "productDetailId") Integer productDetailId) {
		FlashSaleDetailPK flashSaleDetailPK = new FlashSaleDetailPK();
		flashSaleDetailPK.setFlashSaleId(flashSaleId);
		flashSaleDetailPK.setProductDetailId(productDetailId);
		FlashSaleDetailDto flashSaleDetailDto = FlashSaleDetailMapper.toFlashSaleDetailDto(flashSaleDetailService.findByFlashSaleDetailPK(flashSaleDetailPK).get());
		model.addAttribute("flashSaleDetailDto", flashSaleDetailDto);
		
		Integer status;
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleId).get();
		Date now = new Date();
		if (flashSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flashSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-update-flash-sale-detail.html";
	}
	
	@PostMapping(value = "/owner/flash-sale-details/{flashSaleId}/{productDetailId}", params = "update")
	public String updateFlashSaleDetail(@PathVariable(name = "flashSaleId") Integer flashSaleId,  @Valid @ModelAttribute(name = "flashSaleDetailDto") FlashSaleDetailDto flashSaleDetailDto, BindingResult bindingResult, Model model) {
		Integer status;
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleId).get();
		Date now = new Date();
		if (flashSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flashSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		if (status != 1) {
			updateFlashSaleDetailFormValidator.validate(flashSaleDetailDto, bindingResult);
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-flash-sale-detail.html";
		}
		
		flashSaleDetailService.update(flashSaleDetailDto);
		model.addAttribute("hasError", false);
		
		return "owner-update-flash-sale-detail.html";
	}
	
	@PostMapping(value = "/owner/flash-sale-details/{flashSaleId}/{productDetailId}", params = "delete")
	public String deletePurchaseOrderDetail(@PathVariable(name = "flashSaleId") Integer flashSaleId, 
			@PathVariable("productDetailId") Integer productDetailId, RedirectAttributes redirectAttributes, Model model) {
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleId).get();
		Date now = new Date();
		Integer status;
		if (flashSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flashSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		
		if (status == 1 || status == 0) {
			redirectAttributes.addFlashAttribute("isDeletedFlashSaleDetail", false);
			return "redirect:/owner/flash-sale-details?flash-sale-id=" + flashSaleId; 
		}
		
		FlashSaleDetailPK flashSaleDetailPK = new FlashSaleDetailPK();
		flashSaleDetailPK.setFlashSaleId(flashSaleId);
		flashSaleDetailPK.setProductDetailId(productDetailId);
		flashSaleDetailService.deleteByFlashSaleDetailPK(flashSaleDetailPK);
		redirectAttributes.addFlashAttribute("isDeletedFlashSaleDetail", true);
		model.addAttribute("flashSaleId", flashSaleId);
		return "redirect:/owner/flash-sale-details?flash-sale-id=" + flashSaleId; 
	}
	
	@GetMapping(value = "/owner/flash-sale-details", params = {"flash-sale-id", "page-number"})
	public String purchaseOrderDetailPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("flash-sale-id") Integer flashSaleId, Model model) {
		List<FlashSaleDetail> flashSaleDetails = flashSaleDetailService.findAll().stream().filter(flashSaleDetail -> flashSaleDetail.getFlashSale().getFlashSaleId().equals(flashSaleId)).toList();
		model.addAttribute("flashSaleDetails", flashSaleDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("flashSaleId", flashSaleId);
		
		List<BigDecimal> originalPrices = new ArrayList<>();
		List<BigDecimal> salePrices = new ArrayList<>();
		flashSaleDetails.forEach(flashSaleDetail -> {
			BigDecimal originalPrice_ = flashSaleDetail.getProductDetail().getSellingPrices().stream()
					.filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= new Date().getTime())
					.sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			originalPrices.add(originalPrice_);
			salePrices.add(originalPrice_.multiply(new BigDecimal(100 - flashSaleDetail.getFlashSalePercentage())).divide(new BigDecimal(100)));
		});
		model.addAttribute("salePrices", salePrices);
		
		Integer status;
		Date now = new Date();
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleId).get();
		if (flashSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flashSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-flash-sale-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/flash-sale-details", params = {"flash-sale-id", "export"})
	@ResponseBody
	public List<FlashSaleDetailDto> getFlashSaleDetailDtos(@RequestParam("flash-sale-id") Integer flashSaleId) {
		List<FlashSaleDetailDto> flashSaleDetailDtos = FlashSaleDetailMapper.toFlashSaleDetailDtos(flashSaleDetailService.findAll().stream().filter(flashSaleDetail -> flashSaleDetail.getFlashSale().getFlashSaleId().equals(flashSaleId)).toList());
		return flashSaleDetailDtos;
	}
}

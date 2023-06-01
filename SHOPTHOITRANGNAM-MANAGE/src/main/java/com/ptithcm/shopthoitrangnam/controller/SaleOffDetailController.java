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

import com.ptithcm.shopthoitrangnam.dto.SaleOffDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.SaleOffDetailPK;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.mapper.SaleOffDetailMapper;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.SaleOffDetailService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

import jakarta.validation.Valid;

@Controller
public class SaleOffDetailController {
	@Autowired
	SaleOffDetailService saleOffDetailService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	@Qualifier("createSaleOffDetailFormValidator")
	Validator createSaleOffDetailFormValidator;
	
	@GetMapping(value = "/owner/sale-off-details", params = "sale-off-id")
	public String saleOffDetailPage(Model model, @RequestParam("sale-off-id") Integer saleOffId, RedirectAttributes redirectAttributes) {
		List<SaleOffDetail> saleOffDetails = saleOffDetailService.findAll().stream().filter(saleOffDetail -> saleOffDetail.getSaleOffDetailPK().getSaleOffId().equals(saleOffId)).toList();
		model.addAttribute("saleOffDetails", saleOffDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("saleOffId", saleOffId);
		Boolean isDeletedFlatRateSaleDetail = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedFlatRateSaleDetail");
		if (isDeletedFlatRateSaleDetail != null) {
			model.addAttribute("isDeletedFlatRateSaleDetail", isDeletedFlatRateSaleDetail);
		}
		
		List<BigDecimal> originalPrices = new ArrayList<>();
		List<BigDecimal> salePrices = new ArrayList<>();
		saleOffDetails.forEach(saleOffDetail -> {
			BigDecimal originalPrice_ = saleOffDetail.getProductDetail().getSellingPrices().stream()
					.filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= new Date().getTime())
					.sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			originalPrices.add(originalPrice_);
			salePrices.add(originalPrice_.multiply(new BigDecimal(100 - saleOffDetail.getSaleOffPercentage())).divide(new BigDecimal(100)));
		});
		model.addAttribute("salePrices", salePrices);
		
		Integer status;
		Date now = new Date();
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffId).get();
		if (saleOff.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (saleOff.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-sale-off-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/sale-off-details", params = {"sale-off-id", "search"})
	public String searchSaleOffDetail(Model model, @RequestParam("sale-off-id") Integer saleOffId, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/sale-off-details?sale-off-id=" + saleOffId;
		}
		
		search = search.trim();
		Set<SaleOffDetail> saleOffDetails = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			SaleOffDetailPK saleOffDetailPK = new SaleOffDetailPK();
			saleOffDetailPK.setSaleOffId(saleOffId);
			saleOffDetailPK.setProductDetailId(NumberUtils.createInteger(search));
			Optional<SaleOffDetail> saleOffDetail = saleOffDetailService.findBySaleOffDetailPK(saleOffDetailPK);
			if (saleOffDetail.isPresent()) {
				saleOffDetails.add(saleOffDetail.get());
			}
		}
		
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<SaleOffDetail> saleOffDetailsByProductName = saleOffDetailService.findAll().stream().filter(saleOffDetail -> saleOffDetail.getProductDetail().getProduct().getProductName().matches(pattern)).toList();
		saleOffDetails.addAll(saleOffDetailsByProductName);
		 
		model.addAttribute("saleOffDetails", saleOffDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("saleOffId", saleOffId);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		List<BigDecimal> originalPrices = new ArrayList<>();
		List<BigDecimal> salePrices = new ArrayList<>();
		saleOffDetails.forEach(saleOffDetail -> {
			BigDecimal originalPrice_ = saleOffDetail.getProductDetail().getSellingPrices().stream()
					.filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= new Date().getTime())
					.sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			originalPrices.add(originalPrice_);
			salePrices.add(originalPrice_.multiply(new BigDecimal(100 - saleOffDetail.getSaleOffPercentage())).divide(new BigDecimal(100)));
		});
		model.addAttribute("salePrices", salePrices);
		
		Integer status;
		Date now = new Date();
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffId).get();
		if (saleOff.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (saleOff.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-sale-off-detail-manage.html";
	}
	
	@PostMapping(value = "/owner/sale-off-details", params = {"sale-off-id", "create-page"})
	public String createSaleOffDetailPage(Model model, @RequestParam("sale-off-id") Integer saleOffId) {
		SaleOffDetailDto saleOffDetailDto = new SaleOffDetailDto();
		saleOffDetailDto.setSaleOffId(saleOffId);
		model.addAttribute("saleOffDetailDto", saleOffDetailDto);
		
		List<ProductDetail> productDetailsInSaleOffDetails = new ArrayList<>( saleOffDetailService.findBySaleOff(saleOffService.findBySaleOffId(saleOffId).get()).stream().map(saleOffDetail -> saleOffDetail.getProductDetail()).toList());
		List<ProductDetail> productDetails = new ArrayList<>(productDetailService.findAll());
		productDetails.removeAll(productDetailsInSaleOffDetails);
		model.addAttribute("productDetails", productDetails);
		
		return "owner-create-sale-off-detail.html";
	}
	
	@PostMapping(value = "/owner/sale-off-details", params = "create")
	public String createSaleOffDetail(@Valid @ModelAttribute(name = "saleOffDetailDto") SaleOffDetailDto saleOffDetailDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-sale-off-detail.html";
		}
		
		model.addAttribute("hasError", false);
		saleOffDetailService.save(saleOffDetailDto);
		
		List<ProductDetail> productDetailsInSaleOffDetails = new ArrayList<>(saleOffDetailService.findBySaleOff(saleOffService.findBySaleOffId(saleOffDetailDto.getSaleOffId()).get()).stream().map(saleOffDetail -> saleOffDetail.getProductDetail()).toList());
		List<ProductDetail> productDetails = new ArrayList<>(productDetailService.findAll());
		productDetails.removeAll(productDetailsInSaleOffDetails);
		model.addAttribute("productDetails", productDetails);
		
		return "owner-create-sale-off-detail.html";
	}
	
	@PostMapping(value = "/owner/sale-off-details/{saleOffId}/{productDetailId}", params = "update-page")
	public String updateSaleOffDetailPage(Model model, @PathVariable(name = "saleOffId") Integer saleOffId, @PathVariable(name = "productDetailId") Integer productDetailId) {
		SaleOffDetailPK saleOffDetailPK = new SaleOffDetailPK();
		saleOffDetailPK.setSaleOffId(saleOffId);
		saleOffDetailPK.setProductDetailId(productDetailId);
		SaleOffDetailDto saleOffDetailDto = SaleOffDetailMapper.toSaleOffDetailDto(saleOffDetailService.findBySaleOffDetailPK(saleOffDetailPK).get());
		model.addAttribute("saleOffDetailDto", saleOffDetailDto);
		
		Integer status;
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffId).get();
		Date now = new Date();
		if (saleOff.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (saleOff.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-update-sale-off-detail.html";
	}
	
	@PostMapping(value = "/owner/sale-off-details/{saleOffId}/{productDetailId}", params = "update")
	public String updateSaleOffDetail(@PathVariable(name = "saleOffId") Integer saleOffId,  @Valid @ModelAttribute(name = "saleOffDetailDto") SaleOffDetailDto saleOffDetailDto, BindingResult bindingResult, Model model) {
		createSaleOffDetailFormValidator.validate(saleOffDetailDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-sale-off-detail.html";
		}
		
		saleOffDetailService.update(saleOffDetailDto);
		model.addAttribute("hasError", false);
		
		Integer status;
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffId).get();
		Date now = new Date();
		if (saleOff.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (saleOff.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-update-sale-off-detail.html";
	}
	
	@PostMapping(value = "/owner/sale-off-details/{saleOffId}/{productDetailId}", params = "delete")
	public String deleteSaleOffDetail(@PathVariable(name = "saleOffId") Integer saleOffId, 
			@PathVariable("productDetailId") Integer productDetailId, RedirectAttributes redirectAttributes, Model model) {
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffId).get();
		Date now = new Date();
		Integer status;
		if (saleOff.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (saleOff.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		
		if (status == 1 || status == 0) {
			redirectAttributes.addFlashAttribute("isDeletedSaleOffDetail", false);
			return "redirect:/owner/sale-off-details?sale-off-id=" + saleOffId; 
		}
		
		SaleOffDetailPK saleOffDetailPK = new SaleOffDetailPK();
		saleOffDetailPK.setSaleOffId(saleOffId);
		saleOffDetailPK.setProductDetailId(productDetailId);
		saleOffDetailService.deleteBySaleOffDetailPK(saleOffDetailPK);
		redirectAttributes.addFlashAttribute("isDeletedSaleOffDetail", true);
		model.addAttribute("saleOffId", saleOffId);
		return "redirect:/owner/sale-off-details?sale-off-id=" + saleOffId; 
	}
	
	@GetMapping(value = "/owner/sale-off-details", params = {"sale-off-id", "page-number"})
	public String saleOffDetailPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("sale-off-id") Integer saleOffId, Model model) {
		List<SaleOffDetail> saleOffDetails = saleOffDetailService.findAll().stream().filter(saleOffDetail -> saleOffDetail.getSaleOff().getSaleOffId().equals(saleOffId)).toList();
		model.addAttribute("saleOffDetails", saleOffDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("saleOffId", saleOffId);
		
		List<BigDecimal> originalPrices = new ArrayList<>();
		List<BigDecimal> salePrices = new ArrayList<>();
		saleOffDetails.forEach(saleOffDetail -> {
			BigDecimal originalPrice_ = saleOffDetail.getProductDetail().getSellingPrices().stream()
					.filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= new Date().getTime())
					.sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			originalPrices.add(originalPrice_);
			salePrices.add(originalPrice_.multiply(new BigDecimal(100 - saleOffDetail.getSaleOffPercentage())).divide(new BigDecimal(100)));
		});
		model.addAttribute("salePrices", salePrices);
		
		Integer status;
		Date now = new Date();
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffId).get();
		if (saleOff.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (saleOff.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-sale-off-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/sale-off-details", params = {"sale-off-id", "export"})
	@ResponseBody
	public List<SaleOffDetailDto> getSaleOffDetailDtos(@RequestParam("sale-off-id") Integer saleOffId) {
		List<SaleOffDetailDto> saleOffDetailDtos = SaleOffDetailMapper.toSaleOffDetailDtos(saleOffDetailService.findAll().stream().filter(saleOffDetail -> saleOffDetail.getSaleOff().getSaleOffId().equals(saleOffId)).toList());
		return saleOffDetailDtos;
	}
}

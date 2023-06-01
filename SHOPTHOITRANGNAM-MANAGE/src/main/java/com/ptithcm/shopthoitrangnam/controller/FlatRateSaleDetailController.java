package com.ptithcm.shopthoitrangnam.controller;

import java.util.ArrayList;
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

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.FlatRateSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.mapper.FlatRateSaleDetailMapper;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleDetailService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;

import jakarta.validation.Valid;

@Controller
public class FlatRateSaleDetailController {
	@Autowired
	FlatRateSaleDetailService flatRateSaleDetailService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	@Qualifier("createFlatRateSaleDetailFormValidator")
	Validator createFlatRateSaleDetailFormValidator;
	
	@GetMapping(value = "/owner/flat-rate-sale-details", params = "flat-rate-sale-id")
	public String flatRateSaleDetailPage(Model model, @RequestParam("flat-rate-sale-id") Integer flatRateSaleId, RedirectAttributes redirectAttributes) {
		List<FlatRateSaleDetail> flatRateSaleDetails = flatRateSaleDetailService.findAll().stream().filter(flatRateSaleDetail -> flatRateSaleDetail.getFlatRateSaleDetailPK().getFlatRateSaleId().equals(flatRateSaleId)).toList();
		model.addAttribute("flatRateSaleDetails", flatRateSaleDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("flatRateSaleId", flatRateSaleId);
		Boolean isDeletedFlatRateSaleDetail = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedFlatRateSaleDetail");
		if (isDeletedFlatRateSaleDetail != null) {
			model.addAttribute("isDeletedFlatRateSaleDetail", isDeletedFlatRateSaleDetail);
		}
		
		Integer status;
		Date now = new Date();
		FlatRateSale flatRateSale = flatRateSaleService.findByFlatRateSaleId(flatRateSaleId).get();
		if (flatRateSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flatRateSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-flat-rate-sale-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/flat-rate-sale-details", params = {"flat-rate-sale-id", "search"})
	public String searchFlatRateSaleDetail(Model model, @RequestParam("flat-rate-sale-id") Integer flatRateSaleId, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/flat-rate-sale-details?flat-rate-sale-id=" + flatRateSaleId;
		}
		
		search = search.trim();
		Set<FlatRateSaleDetail> flatRateSaleDetails = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			FlatRateSaleDetailPK flatRateSaleDetailPK = new FlatRateSaleDetailPK();
			flatRateSaleDetailPK.setFlatRateSaleId(flatRateSaleId);
			flatRateSaleDetailPK.setProductDetailId(NumberUtils.createInteger(search));
			Optional<FlatRateSaleDetail> flatRateSaleDetail = flatRateSaleDetailService.findByFlatRateSaleDetailPK(flatRateSaleDetailPK);
			if (flatRateSaleDetail.isPresent()) {
				flatRateSaleDetails.add(flatRateSaleDetail.get());
			}
		}
		
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<FlatRateSaleDetail> flatRateSaleDetailsByProductName = flatRateSaleDetailService.findAll().stream().filter(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail().getProduct().getProductName().matches(pattern)).toList();
		flatRateSaleDetails.addAll(flatRateSaleDetailsByProductName);
		 
		model.addAttribute("flatRateSaleDetails", flatRateSaleDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("flatRateSaleId", flatRateSaleId);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		Integer status;
		Date now = new Date();
		FlatRateSale flatRateSale = flatRateSaleService.findByFlatRateSaleId(flatRateSaleId).get();
		if (flatRateSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flatRateSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-flat-rate-sale-detail-manage.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sale-details", params = {"flat-rate-sale-id", "create-page"})
	public String createFlatRateSaleDetailPage(Model model, @RequestParam("flat-rate-sale-id") Integer flatRateSaleId) {
		FlatRateSaleDetailDto flatRateSaleDetailDto = new FlatRateSaleDetailDto();
		flatRateSaleDetailDto.setFlatRateSaleId(flatRateSaleId);
		model.addAttribute("flatRateSaleDetailDto", flatRateSaleDetailDto);
		
		List<ProductDetail> productDetailsInFlatRateSaleDetails = new ArrayList<>(flatRateSaleDetailService.findByFlatRateSale(flatRateSaleService.findByFlatRateSaleId(flatRateSaleId).get()).stream().map(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail()).toList());
		List<ProductDetail> productDetails = new ArrayList<>(productDetailService.findAll());
		productDetails.removeAll(productDetailsInFlatRateSaleDetails);
		model.addAttribute("productDetails", productDetails);
		return "owner-create-flat-rate-sale-detail.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sale-details", params = "create")
	public String createFlatRateSaleDetail(@Valid @ModelAttribute(name = "flatRateSaleDetailDto") FlatRateSaleDetailDto flatRateSaleDetailDto, BindingResult bindingResult, Model model) {
		createFlatRateSaleDetailFormValidator.validate(flatRateSaleDetailDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-flat-rate-sale-detail.html";
		}
		
		model.addAttribute("hasError", false);
		flatRateSaleDetailService.save(flatRateSaleDetailDto);
		
		List<ProductDetail> productDetailsInFlatRateSaleDetails = new ArrayList<>(flatRateSaleDetailService.findByFlatRateSale(flatRateSaleService.findByFlatRateSaleId(flatRateSaleDetailDto.getFlatRateSaleId()).get()).stream().map(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail()).toList());
		List<ProductDetail> productDetails = new ArrayList<>(productDetailService.findAll());
		productDetails.removeAll(productDetailsInFlatRateSaleDetails);
		model.addAttribute("productDetails", productDetails);
		
		return "owner-create-flat-rate-sale-detail.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sale-details/{flatRateSaleId}/{productDetailId}", params = "delete")
	public String deleteFlatRateSaleDetail(@PathVariable(name = "flatRateSaleId") Integer flatRateSaleId, 
			@PathVariable("productDetailId") Integer productDetailId, RedirectAttributes redirectAttributes, Model model) {
		FlatRateSale flatRateSale = flatRateSaleService.findByFlatRateSaleId(flatRateSaleId).get();
		Date now = new Date();
		Integer status;
		if (flatRateSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flatRateSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		
		if (status == 1 || status == 0) {
			redirectAttributes.addFlashAttribute("isDeletedFlatRateSaleDetail", false);
			return "redirect:/owner/flat-rate-sale-details?flat-rate-sale-id=" + flatRateSaleId; 
		}
		
		FlatRateSaleDetailPK flatRateSaleDetailPK = new FlatRateSaleDetailPK();
		flatRateSaleDetailPK.setFlatRateSaleId(flatRateSaleId);
		flatRateSaleDetailPK.setProductDetailId(productDetailId);
		flatRateSaleDetailService.deleteByFlatRateSaleDetailPK(flatRateSaleDetailPK);
		redirectAttributes.addFlashAttribute("isDeletedFlatRateSaleDetail", true);
		model.addAttribute("flatRateSaleId", flatRateSaleId);
		return "redirect:/owner/flat-rate-sale-details?flat-rate-sale-id=" + flatRateSaleId; 
	}
	
	@GetMapping(value = "/owner/flat-rate-sale-details", params = {"flat-rate-sale-id", "page-number"})
	public String flatRateSaleDetailPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("flat-rate-sale-id") Integer flatRateSaleId, Model model) {
		List<FlatRateSaleDetail> flatRateSaleDetails = flatRateSaleDetailService.findAll().stream().filter(flatRateSaleDetail -> flatRateSaleDetail.getFlatRateSale().getFlatRateSaleId().equals(flatRateSaleId)).toList();
		model.addAttribute("flatRateSaleDetails", flatRateSaleDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("flatRateSaleId", flatRateSaleId);
		
		Integer status;
		Date now = new Date();
		FlatRateSale flatRateSale = flatRateSaleService.findByFlatRateSaleId(flatRateSaleId).get();
		if (flatRateSale.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flatRateSale.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-flat-rate-sale-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/flat-rate-sale-details", params = {"flat-rate-sale-id", "export"})
	@ResponseBody
	public List<FlatRateSaleDetailDto> getFlatRateSaleDetailDtos(@RequestParam("flat-rate-sale-id") Integer flatRateSaleId) {
		List<FlatRateSaleDetailDto> flatRateSaleDetailDtos = FlatRateSaleDetailMapper.toFlatRateSaleDetailDtos(flatRateSaleDetailService.findAll().stream().filter(flatRateSaleDetail -> flatRateSaleDetail.getFlatRateSaleDetailPK().getFlatRateSaleId().equals(flatRateSaleId)).toList());
		return flatRateSaleDetailDtos;
	}
}

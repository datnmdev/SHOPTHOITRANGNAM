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

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.mapper.FlatRateSaleMapper;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleDetailService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;

import jakarta.validation.Valid;

@Controller
public class FlatRateSaleController {
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Autowired
	FlatRateSaleDetailService flatRateSaleDetailService;
	
	@Autowired
	@Qualifier("createFlatRateSaleFormValidator")
	Validator createFlatRateSaleFormValidator;
	
	@Autowired
	@Qualifier("updateFlatRateSaleFormValidator")
	Validator updateFlatRateSaleFormValidator;
	
	@GetMapping("/owner/flat-rate-sales")
	public String flatRateSalePage(Model model, RedirectAttributes redirectAttributes) {
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll();
		model.addAttribute("flatRateSales", flatRateSales);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		flatRateSales.forEach(flashSale -> {
			if (flashSale.getEndTime().getTime() < now.getTime()) {
				statuses.add(1); // Đã kết thúc
			} else if (flashSale.getStartTime().getTime() > now.getTime()) {
				statuses.add(-1); // Chưa bắt đầu
			} else {
				statuses.add(0); // Đang diễn ra
			}
		});
		model.addAttribute("statuses", statuses);
		
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedFlatRateSale = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedFlatRateSale");
		if (isDeletedFlatRateSale != null) {
			model.addAttribute("isDeletedFlatRateSale", isDeletedFlatRateSale);
		}
		return "owner-flat-rate-sale-manage.html";
	}
	
	@GetMapping(value = "/owner/flat-rate-sales", params = "search")
	public String searchFlatRateSale(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/flat-rate-sales";
		}
		
		search = search.trim();
		Set<FlatRateSale> flatRateSales = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			Optional<FlatRateSale> flatRateSale = flatRateSaleService.findByFlatRateSaleId(NumberUtils.createInteger(search));
			if (flatRateSale.isPresent()) {
				flatRateSales.add(flatRateSale.get());
			}
		}
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<FlatRateSale> flatRateSalesByName = flatRateSaleService.findByFlatRateSaleNameUsingRegex(pattern);
		flatRateSales.addAll(flatRateSalesByName);
		
		model.addAttribute("flatRateSales", flatRateSales);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		flatRateSales.forEach(flashSale -> {
			if (flashSale.getEndTime().getTime() < now.getTime()) {
				statuses.add(1); // Đã kết thúc
			} else if (flashSale.getStartTime().getTime() > now.getTime()) {
				statuses.add(-1); // Chưa bắt đầu
			} else {
				statuses.add(0); // Đang diễn ra
			}
		});
		model.addAttribute("statuses", statuses);
		
		model.addAttribute("pageNumber", 0);
		return "owner-flat-rate-sale-manage.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sales", params = "create-page")
	public String createFlatRateSalePage(Model model) {
		model.addAttribute("flatRateSaleDto", new FlatRateSaleDto());
		return "owner-create-flat-rate-sale.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sales", params = "create")
	public String createFlatRateSale(@Valid @ModelAttribute(name = "flatRateSaleDto") FlatRateSaleDto flatRateSaleDto, BindingResult bindingResult, Model model) {
		createFlatRateSaleFormValidator.validate(flatRateSaleDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-flat-rate-sale.html";
		}
		
		model.addAttribute("hasError", false);
		flatRateSaleService.save(flatRateSaleDto);
		return "owner-create-flat-rate-sale.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sales/{flatRateSaleId}", params = "update-page")
	public String updateFlatRateSalePage(Model model, @PathVariable(name = "flatRateSaleId") Integer flatRateSaleId) {
		FlatRateSaleDto flatRateSaleDto = FlatRateSaleMapper.toFlatRateSaleDto(flatRateSaleService.findByFlatRateSaleId(flatRateSaleId).get());
		model.addAttribute("flatRateSaleDto", flatRateSaleDto);
		
		Integer status;
		Date now = new Date();
		if (flatRateSaleDto.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flatRateSaleDto.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-update-flat-rate-sale.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sales/{flatRateSaleId}", params = "update")
	public String updateFlatRateSale(@Valid @ModelAttribute(name = "flatRateSaleDto") FlatRateSaleDto flatRateSaleDto, BindingResult bindingResult, Model model) {
		updateFlatRateSaleFormValidator.validate(flatRateSaleDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-flat-rate-sale.html";
		}
		
		model.addAttribute("hasError", false);
		flatRateSaleService.save(flatRateSaleDto);
		return "owner-update-flat-rate-sale.html";
	}
	
	@PostMapping(value = "/owner/flat-rate-sales/{flatRateSaleId}", params = "delete")
	public String deleteFlatRateSale(@PathVariable(name = "flatRateSaleId") Integer flatRateSaleId, RedirectAttributes redirectAttributes) {
		FlatRateSale flatRateSale = flatRateSaleService.findByFlatRateSaleId(flatRateSaleId).get();
		if (flatRateSale.getStartTime().getTime() <= new Date().getTime()) {
			redirectAttributes.addFlashAttribute("isDeletedFlatRateSale", false);
			return "redirect:/owner/flat-rate-sales"; 
		}
		
		flatRateSaleDetailService.deleteAllByFlatRateSale(flatRateSale);
		flatRateSaleService.deleteByFlatRateSaleId(flatRateSaleId);
		redirectAttributes.addFlashAttribute("isDeletedFlatRateSale", true);
		return "redirect:/owner/flat-rate-sales";
	}
	
	@GetMapping(value = "/owner/flat-rate-sales", params = "page-number")
	public String flatRateSalePageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll();
		model.addAttribute("flatRateSales", flatRateSales);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		flatRateSales.forEach(flashSale -> {
			if (flashSale.getEndTime().getTime() < now.getTime()) {
				statuses.add(1); // Đã kết thúc
			} else if (flashSale.getStartTime().getTime() > now.getTime()) {
				statuses.add(-1); // Chưa bắt đầu
			} else {
				statuses.add(0); // Đang diễn ra
			}
		});
		model.addAttribute("statuses", statuses);
		
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-flat-rate-sale-manage.html";
	}
	
	@GetMapping(value = "/owner/flat-rate-sales", params = "export")
	@ResponseBody
	public List<FlatRateSaleDto> getFlatRateSaleDtos() {
		List<FlatRateSaleDto> flatRateSaleDtos = FlatRateSaleMapper.toFlatRateSaleDtos(flatRateSaleService.findAll());
		return flatRateSaleDtos;
	}
}

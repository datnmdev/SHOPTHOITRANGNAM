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

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.mapper.FlashSaleMapper;
import com.ptithcm.shopthoitrangnam.service.FlashSaleDetailService;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;

import jakarta.validation.Valid;

@Controller
public class FlashSaleController {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	FlashSaleDetailService flashSaleDetailService;
	
	@Autowired
	@Qualifier("createFlashSaleFormValidator")
	Validator createFlashSaleFormValidator;
	
	@Autowired
	@Qualifier("updateFlashSaleFormValidator")
	Validator updateFlashSaleFormValidator;
	
	@GetMapping("/owner/flash-sales")
	public String flashSalePage(Model model, RedirectAttributes redirectAttributes) {
		List<FlashSale> flashSales = flashSaleService.findAll();
		model.addAttribute("flashSales", flashSales);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		flashSales.forEach(flashSale -> {
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
		Boolean isDeletedFlashSale = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedFlashSale");
		if (isDeletedFlashSale != null) {
			model.addAttribute("isDeletedFlashSale", isDeletedFlashSale);
		}
		return "owner-flash-sale-manage.html";
	}
	
	@GetMapping(value = "/owner/flash-sales", params = "search")
	public String searchFlashSale(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/flash-sales";
		}
		
		search = search.trim();
		Set<FlashSale> flashSales = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			Optional<FlashSale> flashSale = flashSaleService.findByFlashSaleId(NumberUtils.createInteger(search));
			if (flashSale.isPresent()) {
				flashSales.add(flashSale.get());
			}
		}
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<FlashSale> flashSalesByName = flashSaleService.findByFlashSaleNameUsingRegex(pattern);
		flashSales.addAll(flashSalesByName);
		
		model.addAttribute("flashSales", flashSales);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		flashSales.forEach(flashSale -> {
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
		return "owner-flash-sale-manage.html";
	}
	
	@PostMapping(value = "/owner/flash-sales", params = "create-page")
	public String createFlashSalePage(Model model) {
		model.addAttribute("flashSaleDto", new FlashSaleDto());
		return "owner-create-flash-sale.html";
	}
	
	@PostMapping(value = "/owner/flash-sales", params = "create")
	public String createFlashSale(@Valid @ModelAttribute(name = "flashSaleDto") FlashSaleDto flashSaleDto, BindingResult bindingResult, Model model) {
		createFlashSaleFormValidator.validate(flashSaleDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-flash-sale.html";
		}
		
		model.addAttribute("hasError", false);
		flashSaleService.save(flashSaleDto);
		return "owner-create-flash-sale.html";
	}
	
	@PostMapping(value = "/owner/flash-sales/{flashSaleId}", params = "update-page")
	public String updateFlashSalePage(Model model, @PathVariable(name = "flashSaleId") Integer flashSaleId) {
		FlashSaleDto flashSaleDto = FlashSaleMapper.toFlashSaleDto(flashSaleService.findByFlashSaleId(flashSaleId).get());
		model.addAttribute("flashSaleDto", flashSaleDto);
		
		Integer status;
		Date now = new Date();
		if (flashSaleDto.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (flashSaleDto.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-update-flash-sale.html";
	}
	
	@PostMapping(value = "/owner/flash-sales/{flashSaleId}", params = "update")
	public String updateFlashSale(@Valid @ModelAttribute(name = "flashSaleDto") FlashSaleDto flashSaleDto, BindingResult bindingResult, Model model) {
		updateFlashSaleFormValidator.validate(flashSaleDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-flash-sale.html";
		}
		
		model.addAttribute("hasError", false);
		flashSaleService.save(flashSaleDto);
		return "owner-update-flash-sale.html";
	}
	
	@PostMapping(value = "/owner/flash-sales/{flashSaleId}", params = "delete")
	public String deleteFlashSale(@PathVariable(name = "flashSaleId") Integer flashSaleId, RedirectAttributes redirectAttributes) {
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleId).get();
		if (flashSale.getStartTime().getTime() <= new Date().getTime()) {
			redirectAttributes.addFlashAttribute("isDeletedFlashSale", false);
			return "redirect:/owner/flash-sales"; 
		}
		
		flashSaleDetailService.deleteAllByFlashSale(flashSale);
		flashSaleService.deleteByFlashSaleId(flashSaleId);
		redirectAttributes.addFlashAttribute("isDeletedFlashSale", true);
		return "redirect:/owner/flash-sales";
	}
	
	@GetMapping(value = "/owner/flash-sales", params = "page-number")
	public String flashSalePageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<FlashSale> flashSales = flashSaleService.findAll();
		model.addAttribute("flashSales", flashSales);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		flashSales.forEach(flashSale -> {
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
		return "owner-flash-sale-manage.html";
	}
	
	@GetMapping(value = "/owner/flash-sales", params = "export")
	@ResponseBody
	public List<FlashSaleDto> getFlashSaleDtos() {
		List<FlashSaleDto> flashSaleDtos = FlashSaleMapper.toFlashSaleDtos(flashSaleService.findAll());
		return flashSaleDtos;
	}
}

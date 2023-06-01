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

import com.ptithcm.shopthoitrangnam.dto.SaleOffDto;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.mapper.SaleOffMapper;
import com.ptithcm.shopthoitrangnam.service.SaleOffDetailService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

import jakarta.validation.Valid;

@Controller
public class SaleOffController {
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	SaleOffDetailService saleOffDetailService;
	
	@Autowired
	@Qualifier("createSaleOffFormValidator")
	Validator createSaleOffFormValidator;
	
	@Autowired
	@Qualifier("updateSaleOffFormValidator")
	Validator updateSaleOffFormValidator;
	
	@GetMapping("/owner/sale-offs")
	public String saleOffPage(Model model, RedirectAttributes redirectAttributes) {
		List<SaleOff> saleOffs = saleOffService.findAll();
		model.addAttribute("saleOffs", saleOffs);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		saleOffs.forEach(flashSale -> {
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
		Boolean isDeletedSaleOff = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedSaleOff");
		if (isDeletedSaleOff != null) {
			model.addAttribute("isDeletedSaleOff", isDeletedSaleOff);
		}
		return "owner-sale-off-manage.html";
	}
	
	@GetMapping(value = "/owner/sale-offs", params = "search")
	public String searchSaleOff(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/sale-offs";
		}
		
		search = search.trim();
		Set<SaleOff> saleOffs = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			Optional<SaleOff> saleOff = saleOffService.findBySaleOffId(NumberUtils.createInteger(search));
			if (saleOff.isPresent()) {
				saleOffs.add(saleOff.get());
			}
		}
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<SaleOff> saleOffsByName = saleOffService.findBySaleOffNameUsingRegex(pattern);
		saleOffs.addAll(saleOffsByName);
		
		model.addAttribute("saleOffs", saleOffs);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		saleOffs.forEach(flashSale -> {
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
		return "owner-sale-off-manage.html";
	}
	
	@PostMapping(value = "/owner/sale-offs", params = "create-page")
	public String createSaleOffPage(Model model) {
		model.addAttribute("saleOffDto", new SaleOffDto());
		return "owner-create-sale-off.html";
	}
	
	@PostMapping(value = "/owner/sale-offs", params = "create")
	public String createSaleOff(@Valid @ModelAttribute(name = "saleOffDto") SaleOffDto saleOffDto, BindingResult bindingResult, Model model) {
		createSaleOffFormValidator.validate(saleOffDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-sale-off.html";
		}
		
		model.addAttribute("hasError", false);
		saleOffService.save(saleOffDto);
		return "owner-create-sale-off.html";
	}
	
	@PostMapping(value = "/owner/sale-offs/{saleOffId}", params = "update-page")
	public String updateSaleOffPage(Model model, @PathVariable(name = "saleOffId") Integer saleOffId) {
		SaleOffDto saleOffDto = SaleOffMapper.toOffDto(saleOffService.findBySaleOffId(saleOffId).get());
		model.addAttribute("saleOffDto", saleOffDto);
		
		Integer status;
		Date now = new Date();
		if (saleOffDto.getEndTime().getTime() < now.getTime()) {
			status = 1; // Đã kết thúc
		} else if (saleOffDto.getStartTime().getTime() > now.getTime()) {
			status = -1; // Chưa bắt đầu
		} else {
			status = 0; // Đang diễn ra
		}
		model.addAttribute("status", status);
		
		return "owner-update-sale-off.html";
	}
	
	@PostMapping(value = "/owner/sale-offs/{saleOffId}", params = "update")
	public String updateSaleOff(@Valid @ModelAttribute(name = "saleOffDto") SaleOffDto saleOffDto, BindingResult bindingResult, Model model) {
		updateSaleOffFormValidator.validate(saleOffDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-sale-off.html";
		}
		
		model.addAttribute("hasError", false);
		saleOffService.save(saleOffDto);
		return "owner-update-sale-off.html";
	}
	
	@PostMapping(value = "/owner/sale-offs/{saleOffId}", params = "delete")
	public String deleteSaleOff(@PathVariable(name = "saleOffId") Integer saleOffId, RedirectAttributes redirectAttributes) {
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffId).get();
		if (saleOff.getStartTime().getTime() <= new Date().getTime()) {
			redirectAttributes.addFlashAttribute("isDeletedSaleOff", false);
			return "redirect:/owner/sale-offs"; 
		}
		
		saleOffDetailService.deleteAllBySaleOff(saleOff);
		saleOffService.deleteBySaleOffId(saleOffId);
		redirectAttributes.addFlashAttribute("isDeletedSaleOff", true);
		return "redirect:/owner/sale-offs";
	}
	
	@GetMapping(value = "/owner/sale-offs", params = "page-number")
	public String saleOffPageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<SaleOff> saleOffs = saleOffService.findAll();
		model.addAttribute("saleOffs", saleOffs);
		
		List<Integer> statuses = new ArrayList<>();
		Date now = new Date();
		saleOffs.forEach(flashSale -> {
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
		return "owner-sale-off-manage.html";
	}
	
	@GetMapping(value = "/owner/sale-offs", params = "export")
	@ResponseBody
	public List<SaleOffDto> getSaleOffDtos() {
		List<SaleOffDto> saleOffDtos = SaleOffMapper.toSaleOffDtos(saleOffService.findAll());
		return saleOffDtos;
	}
}

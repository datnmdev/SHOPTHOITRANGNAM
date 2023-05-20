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

import com.ptithcm.shopthoitrangnam.dto.PositionDto;
import com.ptithcm.shopthoitrangnam.dto.SizeDto;
import com.ptithcm.shopthoitrangnam.entity.Position;
import com.ptithcm.shopthoitrangnam.entity.Size;
import com.ptithcm.shopthoitrangnam.mapper.PositionMapper;
import com.ptithcm.shopthoitrangnam.mapper.SizeMapper;
import com.ptithcm.shopthoitrangnam.service.PositionService;
import com.ptithcm.shopthoitrangnam.service.SizeService;

import jakarta.validation.Valid;

@Controller
public class PositionController {
	@Autowired
	PositionService positionService;
	
	@Autowired
	@Qualifier("createPositionFormValidator")
	Validator createPositionFormValidator;
	
	@GetMapping("/owner/positions")
	public String positionPage(Model model, RedirectAttributes redirectAttributes) {
		List<Position> positions = positionService.findAll();
		model.addAttribute("positions", positions);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedPosition = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedPosition");
		if (isDeletedPosition != null) {
			model.addAttribute("isDeletedPosition", isDeletedPosition);
		}
		return "owner-position-manage.html";
	}
	
	@GetMapping(value = "/owner/positions", params = "search")
	public String searchPosition(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/positions";
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Position> positionsByCode = positionService.findByPositionCodeUsingRegex(pattern);
		List<Position> positionsByName = positionService.findByPositionNameUsingRegex(pattern);
		Set<Position> positions = new LinkedHashSet<>(positionsByCode);
		positions.addAll(positionsByName);
		
		model.addAttribute("positions", positions);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-position-manage.html";
	}
	
	@PostMapping(value = "/owner/positions", params = "create-page")
	public String createPositionPage(Model model) {
		model.addAttribute("positionDto", new PositionDto());
		return "owner-create-position.html";
	}
	
	@PostMapping(value = "/owner/positions", params = "create")
	public String createPosition(@Valid @ModelAttribute(name = "positionDto") PositionDto positionDto, BindingResult bindingResult, Model model) {
		createPositionFormValidator.validate(positionDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-position.html";
		}
		
		model.addAttribute("hasError", false);
		positionService.save(positionDto);
		return "owner-create-position.html";
	}
	
	@PostMapping(value = "/owner/positions/{positionCode}", params = "update-page")
	public String updatePositionPage(Model model, @PathVariable(name = "positionCode") String positionCode) {
		PositionDto positionDto = PositionMapper.toPositionDto(positionService.findByPositionCode(positionCode).get());
		model.addAttribute("positionDto", positionDto);
		return "owner-update-position.html";
	}
	
	@PostMapping(value = "/owner/positions/{positionCode}", params = "update")
	public String updatePosition(@Valid @ModelAttribute(name = "positionDto") PositionDto positionDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-position.html";
		}
		
		model.addAttribute("hasError", false);
		positionService.save(positionDto);
		return "owner-update-position.html";
	}
	
	@PostMapping(value = "/owner/positions/{positionCode}", params = "delete")
	public String deletePosition(@PathVariable(name = "positionCode") String positionCode, RedirectAttributes redirectAttributes) {
		if (!positionService.findByPositionCode(positionCode).get().getEmployees().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedPosition", false);
			return "redirect:/owner/positions"; 
		}
		
		positionService.deleteByPositionCode(positionCode);
		redirectAttributes.addFlashAttribute("isDeletedPosition", true);
		return "redirect:/owner/positions";
	}
	
	@GetMapping(value = "/owner/positions", params = "page-number")
	public String positionPageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<Position> positions = positionService.findAll();
		model.addAttribute("positions", positions);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-position-manage.html";
	}
	
	@GetMapping(value = "/owner/positions", params = "export")
	@ResponseBody
	public List<PositionDto> getPositionDtos() {
		List<PositionDto> positionDtos = PositionMapper.toPositionDtos(positionService.findAll());
		return positionDtos;
	}
}

package com.ptithcm.shopthoitrangnam.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.ColorDto;
import com.ptithcm.shopthoitrangnam.entity.Color;
import com.ptithcm.shopthoitrangnam.mapper.ColorMapper;
import com.ptithcm.shopthoitrangnam.service.ColorService;

import jakarta.validation.Valid;

@Controller
public class ColorController {
	@Autowired
	ColorService colorService;
	
	@GetMapping("/owner/colors")
	public String colorPage(Model model, RedirectAttributes redirectAttributes) {
		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedColor = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedColor");
		if (isDeletedColor != null) {
			model.addAttribute("isDeletedColor", isDeletedColor);
		}
		return "owner-color-manage.html";
	}
	
	@GetMapping(value = "/owner/colors", params = "search")
	public String searchColor(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/colors";
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Color> colorsByCategory = colorService.findByColorCodeUsingRegex(pattern);
		List<Color> colorsByName = colorService.findByColorNameUsingRegex(pattern);
		Set<Color> colors = new LinkedHashSet<>(colorsByCategory);
		colors.addAll(colorsByName);
		
		model.addAttribute("colors", colors);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-color-manage.html";
	}
	
	@PostMapping(value = "/owner/colors", params = "create-page")
	public String createColorPage(Model model) {
		model.addAttribute("colorDto", new ColorDto());
		return "owner-create-color.html";
	}
	
	@PostMapping(value = "/owner/colors", params = "create")
	public String createColor(@Valid @ModelAttribute(name = "colorDto") ColorDto colorDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-color.html";
		}
		
		model.addAttribute("hasError", false);
		colorService.save(colorDto);
		return "owner-create-color.html";
	}
	
	@PostMapping(value = "/owner/colors/{colorCode}", params = "update-page")
	public String updateColorPage(Model model, @PathVariable(name = "colorCode") String colorCode) {
		ColorDto colorDto = ColorMapper.toColorDto(colorService.findByColorCode(colorCode).get());
		model.addAttribute("colorDto", colorDto);
		return "owner-update-color.html";
	}
	
	@PostMapping(value = "/owner/colors/{colorCode}", params = "update")
	public String updateSize(@Valid @ModelAttribute(name = "colorDto") ColorDto colorDto, BindingResult bindingResult, Model model) {
		model.addAttribute("colorCode", colorDto.getColorCode());
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-color.html";
		}
		
		model.addAttribute("hasError", false);
		colorService.save(colorDto);
		return "owner-update-color.html";
	}
	
	@PostMapping(value = "/owner/colors/{colorCode}", params = "delete")
	public String deleteColor(@PathVariable(name = "colorCode") String colorCode, RedirectAttributes redirectAttributes) {
		if (!colorService.findByColorCode(colorCode).get().getProductDetails().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedColor", false);
			return "redirect:/owner/colors"; 
		}
		
		colorService.deleteByColorCode(colorCode);
		redirectAttributes.addFlashAttribute("isDeletedColor", true);
		return "redirect:/owner/colors";
	}
	
	@GetMapping(value = "/owner/colors", params = "page-number")
	public String colorPageNumber(@RequestParam("page-number") int pageNumber, Model model) {
		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-color-manage.html";
	}
	
	@GetMapping(value = "/owner/colors", params = "export")
	@ResponseBody
	public List<Color> getColors() {
		List<Color> colors = colorService.findAll();
		return colors;
	}
}

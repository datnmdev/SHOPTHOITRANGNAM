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

import com.ptithcm.shopthoitrangnam.dto.SizeDto;
import com.ptithcm.shopthoitrangnam.entity.Size;
import com.ptithcm.shopthoitrangnam.mapper.SizeMapper;
import com.ptithcm.shopthoitrangnam.service.SizeService;

import jakarta.validation.Valid;

@Controller
public class SizeController {
	@Autowired
	SizeService sizeService;
	
	@Autowired
	@Qualifier("createColorFormValidator")
	Validator createSizeFormValidator;
	
	@GetMapping("/owner/sizes")
	public String sizePage(Model model, RedirectAttributes redirectAttributes) {
		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedSize = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedSize");
		if (isDeletedSize != null) {
			model.addAttribute("isDeletedSize", isDeletedSize);
		}
		return "owner-size-manage.html";
	}
	
	@GetMapping(value = "/owner/sizes", params = "search")
	public String searchSize(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/sizes";
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Size> sizesByCategory = sizeService.findBySizeCodeUsingRegex(pattern);
		List<Size> sizesByName = sizeService.findBySizeNameUsingRegex(pattern);
		Set<Size> sizes = new LinkedHashSet<>(sizesByCategory);
		sizes.addAll(sizesByName);
		
		model.addAttribute("sizes", sizes);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-size-manage.html";
	}
	
	@PostMapping(value = "/owner/sizes", params = "create-page")
	public String createSizePage(Model model) {
		model.addAttribute("sizeDto", new SizeDto());
		return "owner-create-size.html";
	}
	
	@PostMapping(value = "/owner/sizes", params = "create")
	public String createSize(@Valid @ModelAttribute(name = "sizeDto") SizeDto sizeDto, BindingResult bindingResult, Model model) {
		createSizeFormValidator.validate(sizeDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-size.html";
		}
		
		model.addAttribute("hasError", false);
		sizeService.save(sizeDto);
		return "owner-create-size.html";
	}
	
	@PostMapping(value = "/owner/sizes/{sizeCode}", params = "update-page")
	public String updateSizePage(Model model, @PathVariable(name = "sizeCode") String sizeCode) {
		SizeDto sizeDto = SizeMapper.toSizeDto(sizeService.findBySizeCode(sizeCode).get());
		model.addAttribute("sizeDto", sizeDto);
		return "owner-update-size.html";
	}
	
	@PostMapping(value = "/owner/sizes/{sizeCode}", params = "update")
	public String updateSize(@Valid @ModelAttribute(name = "sizeDto") SizeDto sizeDto, BindingResult bindingResult, Model model) {
		model.addAttribute("sizeCode", sizeDto.getSizeCode());
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-size.html";
		}
		
		model.addAttribute("hasError", false);
		sizeService.save(sizeDto);
		return "owner-update-size.html";
	}
	
	@PostMapping(value = "/owner/sizes/{sizecode}", params = "delete")
	public String deleteSize(@PathVariable(name = "sizeCode") String sizeCode, RedirectAttributes redirectAttributes) {
		if (!sizeService.findBySizeCode(sizeCode).get().getProductDetails().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedSize", false);
			return "redirect:/owner/sizes"; 
		}
		
		sizeService.deleteBySizeCode(sizeCode);
		redirectAttributes.addFlashAttribute("isDeletedSize", true);
		return "redirect:/owner/sizes";
	}
	
	@GetMapping(value = "/owner/sizes", params = "page-number")
	public String sizePageNumber(@RequestParam("page-number") int pageNumber, Model model) {
		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-size-manage.html";
	}
	
	@GetMapping(value = "/owner/sizes", params = "export")
	@ResponseBody
	public List<SizeDto> getSizeDtos() {
		List<SizeDto> sizeDtos = SizeMapper.toSizeDtos(sizeService.findAll());
		return sizeDtos;
	}
}

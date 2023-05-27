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

import com.ptithcm.shopthoitrangnam.dto.SupplierDto;
import com.ptithcm.shopthoitrangnam.entity.Supplier;
import com.ptithcm.shopthoitrangnam.mapper.SupplierMapper;
import com.ptithcm.shopthoitrangnam.service.CostPriceService;
import com.ptithcm.shopthoitrangnam.service.SupplierService;
import com.ptithcm.shopthoitrangnam.service.SupplyDetailService;

import jakarta.validation.Valid;

@Controller
public class SupplierController {
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	SupplyDetailService supplyDetailService;
	
	@Autowired
	CostPriceService costPriceService;
	
	@Autowired
	@Qualifier("createSupplierFormValidator")
	Validator createSupplierFormValidator;
	
	@GetMapping("/owner/suppliers")
	public String supplierPage(Model model, RedirectAttributes redirectAttributes) {
		List<Supplier> suppliers = supplierService.findAll();
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedSupplier = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedSupplier");
		if (isDeletedSupplier != null) {
			model.addAttribute("isDeletedSupplier", isDeletedSupplier);
		}
		return "owner-supplier-manage.html";
	}
	
	@GetMapping(value = "/owner/suppliers", params = "search")
	public String searchSupplier(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/suppliers";
		}
		
		search = search.trim();
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Supplier> suppliersByCode = supplierService.findBySupplierCodeUsingRegex(pattern);
		List<Supplier> suppliersByName = supplierService.findBySupplierNameUsingRegex(pattern);
		Set<Supplier> suppliers = new LinkedHashSet<>(suppliersByCode);
		suppliers.addAll(suppliersByName);
		
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-supplier-manage.html";
	}
	
	@PostMapping(value = "/owner/suppliers", params = "create-page")
	public String createSupplierPage(Model model) {
		model.addAttribute("supplierDto", new SupplierDto());
		return "owner-create-supplier.html";
	}
	
	@PostMapping(value = "/owner/suppliers", params = "create")
	public String createSupplier(@Valid @ModelAttribute(name = "supplierDto") SupplierDto supplierDto, BindingResult bindingResult, Model model) {
		createSupplierFormValidator.validate(supplierDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-supplier.html";
		}
		
		model.addAttribute("hasError", false);
		supplierService.save(supplierDto);
		return "owner-create-supplier.html";
	}
	
	@PostMapping(value = "/owner/suppliers/{supplierCode}", params = "update-page")
	public String updateSupplierPage(Model model, @PathVariable(name = "supplierCode") String supplierCode) {
		SupplierDto supplierDto = SupplierMapper.toSupplierDto(supplierService.findBySupplierCode(supplierCode).get());
		model.addAttribute("supplierDto", supplierDto);
		return "owner-update-supplier.html";
	}
	
	@PostMapping(value = "/owner/suppliers/{supplierCode}", params = "update")
	public String updateSupplier(@Valid @ModelAttribute(name = "supplierDto") SupplierDto supplierDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-supplier.html";
		}
		
		model.addAttribute("hasError", false);
		supplierService.save(supplierDto);
		return "owner-update-supplier.html";
	}
	
	@PostMapping(value = "/owner/suppliers/{supplierCode}", params = "delete")
	public String deleteSupplier(@PathVariable(name = "supplierCode") String supplierCode, RedirectAttributes redirectAttributes) {
		Supplier supplier = supplierService.findBySupplierCode(supplierCode).get();
		if (!supplier.getPurchaseOrders().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedSupplier", false);
			return "redirect:/owner/suppliers"; 
		}
		
		costPriceService.deleteAllBySupplier(supplier);
		supplyDetailService.deleteBySupplier(supplier);
		supplierService.deleteBySupplierCode(supplierCode);
		redirectAttributes.addFlashAttribute("isDeletedSupplier", true);
		return "redirect:/owner/suppliers";
	}
	
	@GetMapping(value = "/owner/suppliers", params = "page-number")
	public String supplierPageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<Supplier> suppliers = supplierService.findAll();
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-supplier-manage.html";
	}
	
	@GetMapping(value = "/owner/suppliers", params = "export")
	@ResponseBody
	public List<SupplierDto> getSupplierDtos() {
		List<SupplierDto> supplierDtos = SupplierMapper.toSupplierDtos(supplierService.findAll());
		return supplierDtos;
	}
}

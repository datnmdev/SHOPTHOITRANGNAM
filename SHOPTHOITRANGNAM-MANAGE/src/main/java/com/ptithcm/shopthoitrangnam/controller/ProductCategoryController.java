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

import com.ptithcm.shopthoitrangnam.dto.ProductCategoryDto;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;
import com.ptithcm.shopthoitrangnam.mapper.ProductCategoryMapper;
import com.ptithcm.shopthoitrangnam.service.ProductCategoryService;
import com.ptithcm.shopthoitrangnam.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductCategoryController {
	@Autowired
	ProductCategoryService productCategoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	@Qualifier("createProductCategoryFormValidator")
	Validator createProductCategoryFormValidator;
	
	@GetMapping("/owner/product-categories")
	public String productCategoryPage(Model model, RedirectAttributes redirectAttributes) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedProductCategory = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedProductCategory");
		if (isDeletedProductCategory != null) {
			model.addAttribute("isDeletedProductCategory", isDeletedProductCategory);
		}
		return "owner-product-category-manage.html";
	}
	
	@GetMapping(value = "/owner/product-categories", params = "search")
	public String searchProductCategory(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/product-categories";
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<ProductCategory> productCategoriesByCode = productCategoryService.findByProductCategoryCodeUsingRegex(pattern);
		List<ProductCategory> productCategoriesByName = productCategoryService.findByProductCategoryNameUsingRegex(pattern);
		Set<ProductCategory> productCategories = new LinkedHashSet<>(productCategoriesByCode);
		productCategories.addAll(productCategoriesByName);
		
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-product-category-manage.html";
	}
	
	@PostMapping(value = "/owner/product-categories", params = "create-page")
	public String createProductCategoryPage(Model model) {
		model.addAttribute("productCategoryDto", new ProductCategoryDto());
		return "owner-create-product-category.html";
	}
	
	@PostMapping(value = "/owner/product-categories", params = "create")
	public String createProductCategory(@Valid @ModelAttribute(name = "productCategoryDto") ProductCategoryDto productCategoryDto, BindingResult bindingResult, Model model) {
		createProductCategoryFormValidator.validate(productCategoryDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-product-category.html";
		}
		
		model.addAttribute("hasError", false);
		productCategoryService.save(productCategoryDto);
		return "owner-create-product-category.html";
	}
	
	@PostMapping(value = "/owner/product-categories/{productCategoryCode}", params = "update-page")
	public String updateProductCategoryPage(Model model, @PathVariable(name = "productCategoryCode") String productCategoryCode) {
		ProductCategoryDto productCategoryDto = ProductCategoryMapper.toProductCategoryDto(productCategoryService.findByProductCategoryCode(productCategoryCode).get());
		model.addAttribute("productCategoryDto", productCategoryDto);
		return "owner-update-product-category.html";
	}
	
	@PostMapping(value = "/owner/product-categories/{productCategoryCode}", params = "update")
	public String updateProductCategory(@Valid @ModelAttribute(name = "productCategoryDto") ProductCategoryDto productCategoryDto, BindingResult bindingResult, Model model) {
		model.addAttribute("productCategoryCode", productCategoryDto.getProductCategoryCode());
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-product-category.html";
		}
		
		model.addAttribute("hasError", false);
		productCategoryService.save(productCategoryDto);
		return "owner-update-product-category.html";
	}
	
	@PostMapping(value = "/owner/product-categories/{productCategoryCode}", params = "delete")
	public String deleteSize(@PathVariable(name = "productCategoryCode") String productCategoryCode, RedirectAttributes redirectAttributes) {
		if (!productService.findByProductCategory(productCategoryService.findByProductCategoryCode(productCategoryCode).get()).isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedProductCategory", false);
			return "redirect:/owner/product-categories"; 
		}
		
		productCategoryService.deleteByProductCategoryCode(productCategoryCode);
		redirectAttributes.addFlashAttribute("isDeletedProductCategory", true);
		return "redirect:/owner/product-categories";
	}
	
	@GetMapping(value = "/owner/product-categories", params = "page-number")
	public String productCategoryPageNumber(@RequestParam("page-number") int pageNumber, Model model) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-product-category-manage.html";
	}
	
	@GetMapping(value = "/owner/product-categories", params = "export")
	@ResponseBody
	public List<ProductCategory> getProductCategories() {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		return productCategories;
	}
}

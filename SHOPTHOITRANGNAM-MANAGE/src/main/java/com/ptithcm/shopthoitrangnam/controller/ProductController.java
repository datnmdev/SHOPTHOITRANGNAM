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

import com.ptithcm.shopthoitrangnam.dto.ProductDto;
import com.ptithcm.shopthoitrangnam.entity.Product;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;
import com.ptithcm.shopthoitrangnam.mapper.ProductMapper;
import com.ptithcm.shopthoitrangnam.service.ProductCategoryService;
import com.ptithcm.shopthoitrangnam.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	@Autowired
	ProductCategoryService productCategoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/owner/products")
	public String productsPage(Model model, RedirectAttributes redirectAttributes) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedProduct = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedProduct");
		if (isDeletedProduct != null) {
			model.addAttribute("isDeletedProduct", isDeletedProduct);
		}
		return "owner-product-manage.html";
	}
	
	@GetMapping(value = "/owner/products", params = "search")
	public String searchProduct(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/products";
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Product> productsByCategory = productService.findByProductCategoryCodeUsingRegex(pattern);
		List<Product> productsByName = productService.findByProductNameUsingRegex(pattern);
		Set<Product> products = new LinkedHashSet<>(productsByCategory);
		products.addAll(productsByName);
		
		model.addAttribute("products", products);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-product-manage.html";
	}
	
	@PostMapping(value = "/owner/products", params = "create-page")
	public String createProductPage(Model model) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("productDto", new ProductDto());
		return "owner-create-product.html";
	}
	
	@PostMapping(value = "/owner/products", params = "create")
	public String createProduct(@Valid @ModelAttribute(name = "productDto") ProductDto productDto, BindingResult bindingResult, Model model) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		model.addAttribute("productCategories", productCategories);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-product.html";
		}
		
		model.addAttribute("hasError", false);
		productService.save(productDto);
		return "owner-create-product.html";
	}
	
	@PostMapping(value = "/owner/products/{productCode}", params = "update-page")
	public String updateProductPage(Model model, @PathVariable(name = "productCode") String productCode) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		ProductDto productDto = ProductMapper.toProductDto(productService.findByProductCode(productCode).get());
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("productDto", productDto);
		model.addAttribute("productCode", productCode);
		return "owner-update-product.html";
	}
	
	@PostMapping(value = "/owner/products/{productCode}", params = "update")
	public String updateProduct(@Valid @ModelAttribute(name = "productDto") ProductDto productDto, 
			BindingResult bindingResult, @PathVariable(name = "productCode") String productCode, Model model) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("productCode", productCode);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-product.html";
		}
		
		model.addAttribute("hasError", false);
		productService.save(productCode, productDto);
		return "owner-update-product.html";
	}
	
	@PostMapping(value = "/owner/products/{productCode}", params = "delete")
	public String deleteProduct(@PathVariable(name = "productCode") String productCode, RedirectAttributes redirectAttributes) {
		if (!productService.findByProductCode(productCode).get().getProductDetails().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedProduct", false);
			return "redirect:/owner/products"; 
		}
		
		productService.deleteByProductCode(productCode);
		redirectAttributes.addFlashAttribute("isDeletedProduct", true);
		return "redirect:/owner/products";
	}
	
	@GetMapping(value = "/owner/products", params = "page-number")
	public String productPageNumber(@RequestParam("page-number") int pageNumber, Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-product-manage.html";
	}
	
	@GetMapping(value = "/owner/products", params = "export")
	@ResponseBody
	public List<ProductDto> getProductDtos() {
		List<ProductDto> productDtos = ProductMapper.toProductDtos(productService.findAll());
		return productDtos;
	}
}
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
	
	@GetMapping("/owner/product-manage")
	public String productManagePage(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("pageNumber", 0);
		return "owner-product-manage.html";
	}
	
	@GetMapping("/owner/product-manage/create-product")
	public String createProductPage(Model model) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("productDto", new ProductDto());
		model.addAttribute("hasError", true);
		return "owner-create-product.html";
	}
	
	@PostMapping("/owner/product-manage/create-product")
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
	
	@GetMapping(value = "/owner/product-manage/update-product/{product-code}")
	public String updateProductPage(Model model, @PathVariable(name = "product-code") String productCode) {
		List<ProductCategory> productCategories = productCategoryService.findAll();
		ProductDto productDto = ProductMapper.toProductDto(productService.findByProductCode(productCode).get());
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("productDto", productDto);
		model.addAttribute("productCode", productCode);
		model.addAttribute("hasError", true);
		return "owner-update-product.html";
	}
	
	@PostMapping("/owner/product-manage/update-product/{product-code}")
	public String updateProduct(@Valid @ModelAttribute(name = "productDto") ProductDto productDto, BindingResult bindingResult, @PathVariable(name = "product-code") String productCode, Model model) {
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
	
	@GetMapping(value = "/owner/product-manage/delete-product/{product-code}")
	public String deleteProduct(Model model, @PathVariable(name = "product-code") String productCode) {
		productService.deleteByProductCode(productCode);
		return "redirect:/owner/product-manage";
	}
	
	@GetMapping(value = "/owner/product-manage", params = "search")
	public String searchProduct(@RequestParam(name = "search", defaultValue = "", required = false) String searchKey, Model model) {
		List<Product> productsByCategory = productService.findByProductCategoryCodeUsingRegex(searchKey.replace(' ', '%'));
		List<Product> productsByName = productService.findByProductNameUsingRegex(searchKey.replace(' ', '%'));
		Set<Product> products = new LinkedHashSet<>(productsByCategory);
		products.addAll(productsByName);
		
		model.addAttribute("products", products);
		return "owner-product-manage.html";
	}
	
	@GetMapping(value = "/owner/product-manage", params = "page-number")
	public String productPageNumber(@RequestParam("page-number") int pageNumber, Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-product-manage.html";
	}
	
	@GetMapping("/owner/product-manage/products")
	@ResponseBody
	public List<Product> getProducts() {
		List<Product> products = productService.findAll();
		return products;
	}
}
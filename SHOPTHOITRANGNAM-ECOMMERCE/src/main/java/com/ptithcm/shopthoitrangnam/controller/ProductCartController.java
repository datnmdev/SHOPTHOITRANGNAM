package com.ptithcm.shopthoitrangnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.ShoppingCartDto;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.entity.ShoppingCart;
import com.ptithcm.shopthoitrangnam.service.AccountService;
import com.ptithcm.shopthoitrangnam.service.CustomerService;
import com.ptithcm.shopthoitrangnam.service.ShoppingCartService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductCartController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@GetMapping(value = "/user/product-carts", params = "add")
	public String addProductInCart(Model model, HttpServletRequest httpServletRequest,
			@RequestParam("add") Integer productDetailId, RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.findByAccount(accountService.findByUsername(authentication.getName()).get()).get();
		
		ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
		shoppingCartDto.setCustomerCode(customer.getCustomerCode());
		shoppingCartDto.setProductDetailId(productDetailId);
		shoppingCartDto.setQuantity(1);
		shoppingCartService.save(shoppingCartDto);
		
		redirectAttributes.addFlashAttribute("hasError", false);
		
		return "redirect:/user/product-carts";
	}
	
	@GetMapping(value = "/user/product-carts")
	public String productCart(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.findByAccount(accountService.findByUsername(authentication.getName()).get()).get();
		
		List<ShoppingCart> shoppingCarts = customer.getShoppingCarts();
		model.addAttribute("shoppingCarts", shoppingCarts);
		
		return "product-cart.html";
	}
}

package com.ptithcm.shopthoitrangnam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping(value = "/login", params = {"username", "error"})
	public String loginError(Model model, @RequestParam("username") String username) {
		model.addAttribute("username", username);
		model.addAttribute("hasError", true);
		return "index.html";
	}
}

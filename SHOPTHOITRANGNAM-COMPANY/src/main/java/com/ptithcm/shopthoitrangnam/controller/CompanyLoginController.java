package com.ptithcm.shopthoitrangnam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyLoginController {
	@GetMapping("/company/login")
	public String companyLogin() {
		return "seller-login.html";
	}
	
	@GetMapping(name = "/company/login/error")
	public String companySuccessLogin() {
		return "home.html";
	}
}

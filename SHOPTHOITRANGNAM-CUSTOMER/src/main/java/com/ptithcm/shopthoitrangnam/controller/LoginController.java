package com.ptithcm.shopthoitrangnam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
}

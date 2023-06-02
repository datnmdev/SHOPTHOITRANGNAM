package com.ptithcm.shopthoitrangnam.controller;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptithcm.shopthoitrangnam.dto.AccountDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.mapper.AccountMapper;
import com.ptithcm.shopthoitrangnam.service.AccountService;
import com.ptithcm.shopthoitrangnam.service.CustomerService;
import com.ptithcm.shopthoitrangnam.service.MailService;
import com.ptithcm.shopthoitrangnam.service.RedisService;

import jakarta.mail.MessagingException;

@Controller
public class LoginController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	MailService mailService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login.html";
	}
	
	@GetMapping(value = "/login", params = {"error"})
	public String loginError(Model model) {
		model.addAttribute("hasError", true);
		return "login.html";
	}
	
	@PostMapping(value = "/forgot-password", params = "username")
	public String forgotPassword(Model model, @RequestParam("username") String username) throws MessagingException {
		Optional<Account> account = accountService.findByUsername(username);
		if (account.isPresent()) {
			Random rd = new Random();
			Integer rdNumber = rd.nextInt(1000000);
			String verifyCode = ("000000" + rdNumber.toString()).substring(rdNumber.toString().length());
			redisService.setValue(username, verifyCode);
			
			String mailTo = customerService.findByAccount(accountService.findByUsername(username).get()).get().getEmail();
			String subject = "Shop thời trang nam - Quên mật khẩu";
			String htmlContent = "<p>Mã xác nhận của bạn là " + "<span style='color: red;font-weight:500;'>" + verifyCode + "</span></p>"; 
			mailService.sendHtmlEmail(mailTo, subject, htmlContent);
			
			model.addAttribute("username", username);
			int index = mailTo.indexOf("@") - 1;
			String anonymousEmail = "";
			for (int i = 0; i < mailTo.length(); ++i) {
				if (i > 0 && i < index) {
					anonymousEmail += "*";
				} else {
					anonymousEmail += mailTo.charAt(i);
				}
			}
			model.addAttribute("anonymousEmail", anonymousEmail);
			
			return "enter-verification-code.html";
		} else {
			model.addAttribute("hasUserNameError", true);
			model.addAttribute("username", username);
			return "login.html";
		}
	}
	
	@PostMapping(value = "/reset-password", params = "reset-password-page")
	public String resetPasswordPage(Model model, @RequestParam("verify-code") String verifyCode, @RequestParam("username") String username) {
		if (!redisService.getValue(username).equals(verifyCode)) {
			model.addAttribute("hasError", true);
			model.addAttribute("username", username);
			String email = customerService.findByAccount(accountService.findByUsername(username).get()).get().getEmail();
			int index = email.indexOf("@") - 1;
			String anonymousEmail = "";
			for (int i = 0; i < email.length(); ++i) {
				if (i > 0 && i < index) {
					anonymousEmail += "*";
				} else {
					anonymousEmail += email.charAt(i);
				}
			}
			model.addAttribute("anonymousEmail", anonymousEmail);
			model.addAttribute("anonymousEmail", anonymousEmail);
			return "enter-verification-code.html";
		}
		
		String uuid = UUID.randomUUID().toString();
		redisService.setValue(username, uuid);
		model.addAttribute("username", username);
		model.addAttribute("uuid", uuid);
		return "reset-password.html";
	}
	
	@PostMapping(value = "/reset-password", params = "save")
	public String resetPassword(Model model, @RequestParam("uuid") String uuid, 
			@RequestParam("username") String username, @RequestParam("password") String password, 
			@RequestParam("confirm-password") String confirmPassword) {
		if (!confirmPassword.equals(password)) {
			model.addAttribute("hasError", true);
			model.addAttribute("username", username);
			model.addAttribute("uuid", uuid);
			return "reset-password.html";
		}
		
		if (!redisService.getValue(username).equals(uuid)) {
			return "redirect:/login";
		}
		
		Account account = accountService.findByUsername(username).get();
		AccountDto accountDto = AccountMapper.toAccountDto(account);
		accountDto.setPassword(password);
		accountService.update(accountDto);
		return "reset-password-success.html";
	}
}

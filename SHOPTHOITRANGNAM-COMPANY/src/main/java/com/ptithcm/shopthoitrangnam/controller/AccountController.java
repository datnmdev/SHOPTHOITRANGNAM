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

import com.ptithcm.shopthoitrangnam.dto.AccountDto;
import com.ptithcm.shopthoitrangnam.dto.ProductDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.AccountType;
import com.ptithcm.shopthoitrangnam.entity.Product;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;
import com.ptithcm.shopthoitrangnam.mapper.AccountMapper;
import com.ptithcm.shopthoitrangnam.mapper.ProductMapper;
import com.ptithcm.shopthoitrangnam.service.AccountService;
import com.ptithcm.shopthoitrangnam.service.AccountTypeService;
import com.ptithcm.shopthoitrangnam.service.CustomerService;
import com.ptithcm.shopthoitrangnam.service.EmployeeService;
import com.ptithcm.shopthoitrangnam.service.ProductCategoryService;
import com.ptithcm.shopthoitrangnam.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	@Qualifier("createAccountFormValidator")
	Validator createAccountFormValidator;
	
	@Autowired
	@Qualifier("updateAccountFormValidator")
	Validator updateAccountFormValidator;
	
	@GetMapping("/owner/accounts")
	public String accountPage(Model model, RedirectAttributes redirectAttributes) {
		List<Account> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedAccount = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedAccount");
		if (isDeletedAccount != null) {
			model.addAttribute("isDeletedAccount", isDeletedAccount);
		}
		return "owner-account-manage.html";
	}
	
	@GetMapping(value = "/owner/accounts", params = "search")
	public String searchAccount(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/accounts";
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Account> accounts = accountService.findByUsernameUsingRegex(pattern);
		
		model.addAttribute("accounts", accounts);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-account-manage.html";
	}
	
	@PostMapping(value = "/owner/accounts", params = "create-page")
	public String createAccount(Model model) {
		List<AccountType> accountTypes = accountTypeService.findAll().stream().filter(accountType -> !accountType.getAccountTypeCode().equals("customer")).toList();
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("accountDto", new AccountDto());
		return "owner-create-account.html";
	}
	
	@PostMapping(value = "/owner/accounts", params = "create")
	public String createAccount(@Valid @ModelAttribute(name = "accountDto") AccountDto accountDto, BindingResult bindingResult, Model model) {
		List<AccountType> accountTypes = accountTypeService.findAll().stream().filter(accountType -> !accountType.getAccountTypeCode().equals("customer")).toList();
		model.addAttribute("accountTypes", accountTypes);
		
		createAccountFormValidator.validate(accountDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-account.html";
		}
		
		model.addAttribute("hasError", false);
		accountService.insert(accountDto);
		return "owner-create-account.html";
	}
	
	@PostMapping(value = "/owner/accounts/{username}", params = "update-page")
	public String updateAccountPage(Model model, @PathVariable(name = "username") String username) {
		List<AccountType> accountTypes = accountTypeService.findAll().stream().filter(accountType -> !accountType.getAccountTypeCode().equals("customer")).toList();
		AccountDto accountDto = AccountMapper.toAccountDto(accountService.findByUsername(username).get());
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("accountDto", accountDto);
		return "owner-update-account.html";
	}
	
	@PostMapping(value = "/owner/accounts/{username}", params = "update")
	public String updateProduct(@Valid @ModelAttribute(name = "accountDto") AccountDto accountDto, BindingResult bindingResult, Model model) {
		List<AccountType> accountTypes = accountTypeService.findAll().stream().filter(accountType -> !accountType.getAccountTypeCode().equals("customer")).toList();
		model.addAttribute("accountTypes", accountTypes);
		
		updateAccountFormValidator.validate(accountDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-account.html";
		}
		
		model.addAttribute("hasError", false);
		accountService.update(accountDto);
		return "owner-update-account.html";
	}
	
	@PostMapping(value = "/owner/accounts/{username}", params = "delete")
	public String deleteAccount(@PathVariable(name = "username") String username, RedirectAttributes redirectAttributes) {
		Account account = accountService.findByUsername(username).get();
		if (employeeService.findByAccount(account).isPresent() || customerService.findByAccount(account).isPresent()) {
			redirectAttributes.addFlashAttribute("isDeletedAccount", false);
			return "redirect:/owner/accounts"; 
		}
		
		accountService.deleteByUsername(username);
		redirectAttributes.addFlashAttribute("isDeletedAccount", true);
		return "redirect:/owner/accounts";
	}
	
	@GetMapping(value = "/owner/accounts", params = "page-number")
	public String accountPageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<Account> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-account-manage.html";
	}
	
	@GetMapping(value = "/owner/accounts", params = "export")
	@ResponseBody
	public List<Account> getAccounts() {
		List<Account> accounts = accountService.findAll();
		return accounts;
	}
}

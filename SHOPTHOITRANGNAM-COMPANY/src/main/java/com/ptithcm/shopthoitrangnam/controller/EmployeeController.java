package com.ptithcm.shopthoitrangnam.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.EmployeeDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.entity.Position;
import com.ptithcm.shopthoitrangnam.mapper.EmployeeMapper;
import com.ptithcm.shopthoitrangnam.service.AccountService;
import com.ptithcm.shopthoitrangnam.service.EmployeeService;
import com.ptithcm.shopthoitrangnam.service.PositionService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	@Qualifier("createEmployeeFormValidator")
	Validator createEmployeeFormValidator;
	
	@Autowired
	@Qualifier("updateEmployeeFormValidator")
	Validator updateEmployeeFormValidator;
	
	@Value("${multipart.saveEmployeeImagePath}")
	private String saveEmployeeImagePath;
	
	@Value("${image.employeeDefaultImage}")
	private String employeeDefaultImage;
	
	@GetMapping("/owner/employees")
	public String employeePage(Model model, RedirectAttributes redirectAttributes) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedEmployee = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedEmployee");
		if (isDeletedEmployee != null) {
			model.addAttribute("isDeletedEmployee", isDeletedEmployee);
		}
		return "owner-employee-manage.html";
	}
	
	@GetMapping(value = "/owner/employees", params = "search")
	public String searchEmployee(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/employees";
		}
		
		Set<Employee> employees = new LinkedHashSet<>();
		search = search.trim();
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Employee> employeesByCode = employeeService.findByEmployeeCodeUsingRegex(pattern);
		List<Employee> employeesByFullName = employeeService.findByEmployeeFullNameUsingRegex(pattern);
		
		employees.addAll(employeesByCode);
		employees.addAll(employeesByFullName);
		
		model.addAttribute("employees", employees);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-employee-manage.html";
	}
	
	@PostMapping(value = "/owner/employees", params = "create-page")
	public String createEmployeePage(Model model) {
		List<Position> positions = positionService.findAll();
		List<Account> accounts = accountService.findAll().stream().filter(account -> !employeeService.findByAccount(account).isPresent()).toList();
		
		model.addAttribute("positions", positions);
		model.addAttribute("accounts", accounts);
		
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setJobStatus(true);
		employeeDto.setImage(employeeDefaultImage);
		model.addAttribute("employeeDto", employeeDto);
		return "owner-create-employee.html";
	}
	
	@PostMapping(value = "/owner/employees", params = "create")
	public String createEmployee(@Valid @ModelAttribute(name = "employeeDto") EmployeeDto employeeDto,
			BindingResult bindingResult, @RequestPart("employeeImage") MultipartFile file,  Model model) throws IllegalStateException, IOException {
		List<Position> positions = positionService.findAll();
		List<Account> accounts = accountService.findAll().stream().filter(account -> !employeeService.findByAccount(account).isPresent()).toList();
		
		model.addAttribute("positions", positions);
		model.addAttribute("accounts", accounts);
		
		createEmployeeFormValidator.validate(employeeDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			employeeDto.setImage(employeeDefaultImage);
			return "owner-create-employee.html";
		}
		
		model.addAttribute("hasError", false);
		
//		Save image
		if (!file.isEmpty()) {
			String fileExtention = employeeDto.getImage().substring(employeeDto.getImage().lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString() + fileExtention;
			Path destPath = Paths.get(saveEmployeeImagePath + "/" + newFileName);
			file.transferTo(destPath);
			employeeDto.setImage("/img/employees/" + newFileName);
		}
		employeeService.save(employeeDto);
		employeeDto.setImage(employeeDefaultImage);
		return "owner-create-employee.html";
	}
	
	@PostMapping(value = "/owner/employees/{employeeCode}", params = "update-page")
	public String updateEmployeePage(Model model, @PathVariable(name = "employeeCode") String employeeCode) {
		List<Position> positions = positionService.findAll();
		List<Account> accounts = accountService.findAll().stream().filter(account -> !employeeService.findByAccount(account).isPresent()).toList();
		model.addAttribute("positions", positions);
		model.addAttribute("accounts", accounts);
		EmployeeDto employeeDto = EmployeeMapper.toEmployeeDto(employeeService.findByEmployeeCode(employeeCode).get());
		model.addAttribute("employeeDto", employeeDto);
		return "owner-update-employee.html";
	}
	
	@PostMapping(value = "/owner/employees/{employeeCode}", params = "update")
	public String updateEmployee(@Valid @ModelAttribute(name = "employeeDto") EmployeeDto employeeDto, 
			@RequestPart("employeeImage") MultipartFile file, BindingResult bindingResult, Model model) throws IllegalStateException, IOException {
		List<Position> positions = positionService.findAll();
		List<Account> accounts = accountService.findAll().stream().filter(account -> !employeeService.findByAccount(account).isPresent()).toList();
		model.addAttribute("positions", positions);
		model.addAttribute("accounts", accounts);
		updateEmployeeFormValidator.validate(employeeDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-employee.html";
		}
		
//		Save image
		if (!file.isEmpty()) {
			String fileExtention = employeeDto.getImage().substring(employeeDto.getImage().lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString() + fileExtention;
			Path destPath = Paths.get(saveEmployeeImagePath + "/" + newFileName);
			file.transferTo(destPath);
			employeeDto.setImage("/img/employees/" + newFileName);
		}
		employeeService.save(employeeDto);
		model.addAttribute("hasError", false);
		return "owner-update-employee.html";
	}
	
	@PostMapping(value = "/owner/employees/{employeeCode}", params = "delete")
	public String deleteEmployee(@PathVariable(name = "employeeCode") String employeeCode, RedirectAttributes redirectAttributes) {
		Employee employee = employeeService.findByEmployeeCode(employeeCode).get();
		if (!employee.getOrderPreparationDetails().isEmpty() && !employee.getDeliveryNotes().isEmpty() 
				&& !employee.getCreatorDeliveryNotes().isEmpty() && !employee.getPurchaseNotes().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedEmployee", false);
			return "redirect:/owner/employees"; 
		}
		
		employeeService.deleteByEmployeeCode(employeeCode);
		Account account = employee.getAccount();
		if (account != null) {
			accountService.deleteByUsername(account.getUsername());
		}
		redirectAttributes.addFlashAttribute("isDeletedEmployee", true);
		return "redirect:/owner/employees";
	}
	
	@GetMapping(value = "/owner/employees", params = "page-number")
	public String employeePageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-employee-manage.html";
	}
	
	@GetMapping(value = "/owner/employees", params = "export")
	@ResponseBody
	public List<EmployeeDto> getEmployeeDtos() {
		List<EmployeeDto> employeeDtos = EmployeeMapper.toEmployeeDtos(employeeService.findAll());
		return employeeDtos;
	}
}

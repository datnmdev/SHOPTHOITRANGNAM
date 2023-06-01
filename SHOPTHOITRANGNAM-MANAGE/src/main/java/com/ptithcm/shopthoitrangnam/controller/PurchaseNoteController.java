package com.ptithcm.shopthoitrangnam.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;
import com.ptithcm.shopthoitrangnam.mapper.PurchaseNoteMapper;
import com.ptithcm.shopthoitrangnam.service.DeliveryNoteService;
import com.ptithcm.shopthoitrangnam.service.EmployeeService;
import com.ptithcm.shopthoitrangnam.service.PurchaseNoteService;

@Controller
public class PurchaseNoteController {
	@Autowired
	PurchaseNoteService purchaseNoteService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DeliveryNoteService deliveryNoteService;
	
	@GetMapping(value = "/owner/purchase-notes", params = "employee-code")
	public String purchaseNotePage(Model model, @RequestParam("employee-code") String employeeCode, RedirectAttributes redirectAttributes) {
		List<PurchaseNote> purchaseNotes = purchaseNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get());
		model.addAttribute("purchaseNotes", purchaseNotes);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("employeeCode", employeeCode);

		Integer quantityOfPurchaseNote = purchaseNotes.size();
		Integer quantityOfDeliveryNote = deliveryNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()).size();
		model.addAttribute("quantityOfPurchaseNote", quantityOfPurchaseNote);
		model.addAttribute("quantityOfDeliveryNote", quantityOfDeliveryNote);
		
		return "owner-purchase-note.html";
	}
	
	@GetMapping(value = "/owner/purchase-notes", params = {"employee-code", "search"})
	public String searchPurchaseOrder(Model model, @RequestParam("employee-code") String employeeCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/purchase-notes?employee-code=" + employeeCode;
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<PurchaseNote> purchaseNotesByCode = purchaseNoteService.findByPurchaseNoteCodeUsingRegex(pattern);
		List<PurchaseNote> purchaseNotesByName = purchaseNoteService.findByPurchaseNoteNameUsingRegex(pattern);
		Set<PurchaseNote> purchaseNotes = new LinkedHashSet<>(purchaseNotesByCode);
		purchaseNotes.addAll(purchaseNotesByName);
		 
		model.addAttribute("purchaseNotes", purchaseNotes);
		model.addAttribute("isSearched", true);
		model.addAttribute("employeeCode", employeeCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		Integer quantityOfPurchaseNote = purchaseNoteService.findAll().size();
		Integer quantityOfDeliveryNote = deliveryNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()).size();
		model.addAttribute("quantityOfPurchaseNote", quantityOfPurchaseNote);
		model.addAttribute("quantityOfDeliveryNote", quantityOfDeliveryNote);
		
		return "owner-purchase-note.html";
	}
	
	@GetMapping(value = "/owner/purchase-notes", params = {"employee-code", "page-number"})
	public String purchaseOrderPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("employee-code") String employeeCode, Model model) {
		List<PurchaseNote> purchaseNotes = purchaseNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get());
		model.addAttribute("purchaseNotes", purchaseNotes);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("employeeCode", employeeCode);
		
		Integer quantityOfPurchaseNote = purchaseNotes.size();
		Integer quantityOfDeliveryNote = deliveryNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()).size();
		model.addAttribute("quantityOfPurchaseNote", quantityOfPurchaseNote);
		model.addAttribute("quantityOfDeliveryNote", quantityOfDeliveryNote);
		
		return "owner-purchase-note.html";
	}
	
	@GetMapping(value = "/owner/purchase-notes", params = {"employee-code", "export"})
	@ResponseBody
	public List<PurchaseNoteDto> getPurchaseNoteDtos(@RequestParam("employee-code") String employeeCode) {
		List<PurchaseNoteDto> purchaseNoteDtos = PurchaseNoteMapper.toPurchaseNoteDtos(purchaseNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()));
		return purchaseNoteDtos;
	}
}

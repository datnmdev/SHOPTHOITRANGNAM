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

import com.ptithcm.shopthoitrangnam.dto.DeliveryNoteDto;
import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;
import com.ptithcm.shopthoitrangnam.mapper.DeliveryNoteMapper;
import com.ptithcm.shopthoitrangnam.service.DeliveryNoteService;
import com.ptithcm.shopthoitrangnam.service.EmployeeService;
import com.ptithcm.shopthoitrangnam.service.PurchaseNoteService;

@Controller
public class DeliveryNoteController {
	@Autowired
	DeliveryNoteService deliveryNoteService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PurchaseNoteService purchaseNoteService;
	
	@GetMapping(value = "/owner/delivery-notes", params = "employee-code")
	public String deliveryNotePage(Model model, @RequestParam("employee-code") String employeeCode, RedirectAttributes redirectAttributes) {
		List<DeliveryNote> deliveryNotes = deliveryNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get());
		model.addAttribute("deliveryNotes", deliveryNotes);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("employeeCode", employeeCode);
		
		Integer quantityOfPurchaseNote = purchaseNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()).size();
		Integer quantityOfDeliveryNote = deliveryNotes.size();
		model.addAttribute("quantityOfPurchaseNote", quantityOfPurchaseNote);
		model.addAttribute("quantityOfDeliveryNote", quantityOfDeliveryNote);
		
		return "owner-delivery-note.html";
	}
	
	@GetMapping(value = "/owner/delivery-notes", params = {"employee-code", "search"})
	public String searchDeliveryNote(Model model, @RequestParam("employee-code") String employeeCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/delivery-notes?employee-code=" + employeeCode;
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<DeliveryNote> deliveryNotesByCode = deliveryNoteService.findByDeliveryNoteCodeUsingRegex(pattern);
		List<DeliveryNote> deliveryNotesByName = deliveryNoteService.findByDeliveryNoteNameUsingRegex(pattern);
		Set<DeliveryNote> deliveryNotes = new LinkedHashSet<>(deliveryNotesByCode);
		deliveryNotes.addAll(deliveryNotesByName);
		 
		model.addAttribute("deliveryNotes", deliveryNotes);
		model.addAttribute("isSearched", true);
		model.addAttribute("employeeCode", employeeCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		Integer quantityOfPurchaseNote = purchaseNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()).size();
		Integer quantityOfDeliveryNote = deliveryNoteService.findAll().size();
		model.addAttribute("quantityOfPurchaseNote", quantityOfPurchaseNote);
		model.addAttribute("quantityOfDeliveryNote", quantityOfDeliveryNote);
		
		return "owner-delivery-note.html";
	}
	
	@GetMapping(value = "/owner/delivery-notes", params = {"employee-code", "page-number"})
	public String purchaseOrderPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("employee-code") String employeeCode, Model model) {
		List<DeliveryNote> deliveryNotes = deliveryNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get());
		model.addAttribute("deliveryNotes", deliveryNotes);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("employeeCode", employeeCode);
		
		Integer quantityOfPurchaseNote = purchaseNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()).size();
		Integer quantityOfDeliveryNote = deliveryNotes.size();
		model.addAttribute("quantityOfPurchaseNote", quantityOfPurchaseNote);
		model.addAttribute("quantityOfDeliveryNote", quantityOfDeliveryNote);
		
		return "owner-delivery-note.html";
	}
	
	@GetMapping(value = "/owner/delivery-notes", params = {"employee-code", "export"})
	@ResponseBody
	public List<DeliveryNoteDto> getDeliveryNoteDtos(@RequestParam("employee-code") String employeeCode) {
		List<DeliveryNoteDto> deliveryNoteDtos = DeliveryNoteMapper.toDeliveryNoteDtos(deliveryNoteService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()));
		return deliveryNoteDtos;
	}
}

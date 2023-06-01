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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.OrderPreparationDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.OrderPreparationDetailPK;
import com.ptithcm.shopthoitrangnam.entity.Order;
import com.ptithcm.shopthoitrangnam.entity.OrderPreparationDetail;
import com.ptithcm.shopthoitrangnam.mapper.OrderPreparationDetailMapper;
import com.ptithcm.shopthoitrangnam.service.EmployeeService;
import com.ptithcm.shopthoitrangnam.service.OrderPreparationDetailService;
import com.ptithcm.shopthoitrangnam.service.OrderService;

import jakarta.validation.Valid;

@Controller
public class OrderPreparationDetailController {
	@Autowired
	OrderPreparationDetailService orderPreparationDetailService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value = "/owner/order-preparation-details", params = "employee-code")
	public String orderPreparationDetailPage(Model model, @RequestParam("employee-code") String employeeCode, RedirectAttributes redirectAttributes) {
		List<OrderPreparationDetail> orderPreparationDetails = orderPreparationDetailService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get());
		model.addAttribute("orderPreparationDetails", orderPreparationDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("employeeCode", employeeCode);
		Boolean isDeletedOrderPreparationDetail = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedOrderPreparationDetail");
		if (isDeletedOrderPreparationDetail != null) {
			model.addAttribute("isDeletedOrderPreparationDetail", isDeletedOrderPreparationDetail);
		}
		
		return "owner-order-preparation-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/order-preparation-details", params = {"employee-code", "search"})
	public String searchOrderPreparationDetail(Model model, @RequestParam("employee-code") String employeeCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/order-preparation-details?purchaseOrderDetail=" + employeeCode;
		}
		
		Set<OrderPreparationDetail> orderPreparationDetails = new LinkedHashSet<>();
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<OrderPreparationDetail> orderPreparationDetailsByProductName = orderPreparationDetailService.findAll().stream().filter(orderPreparationDetail -> orderPreparationDetail.getOrderPreparationDetailPK().getOrderCode().matches(pattern)).toList();
		orderPreparationDetails.addAll(orderPreparationDetailsByProductName);
		 
		model.addAttribute("orderPreparationDetails", orderPreparationDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("employeeCode", employeeCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		return "owner-order-preparation-detail-manage.html";
	}
	
	@PostMapping(value = "/owner/order-preparation-details", params = {"employee-code", "create-page"})
	public String createOrderPreparationDetailPage(Model model, @RequestParam("employee-code") String employeeCode) {
		OrderPreparationDetailDto orderPreparationDetailDto = new OrderPreparationDetailDto();
		orderPreparationDetailDto.setEmployeeCode(employeeCode);
		model.addAttribute("orderPreparationDetailDto", orderPreparationDetailDto);
		
		List<Order> ordersInOrderPreparationDetails= orderPreparationDetailService.findAll().stream().map(orderPreparationDetail -> orderPreparationDetail.getOrder()).toList();
		List<Order> orders = orderService.findAll();
		orders.removeAll(ordersInOrderPreparationDetails);
		model.addAttribute("orders", orders);
		return "owner-create-order-preparation-detail.html";
	}
	
	@PostMapping(value = "/owner/order-preparation-details", params = "create")
	public String createOrderPreparationDetail(@Valid @ModelAttribute(name = "orderPreparationDetailDto") OrderPreparationDetailDto orderPreparationDetailDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-create-order-preparation-detail.html";
		}
		
		model.addAttribute("hasError", false);
		orderPreparationDetailService.save(orderPreparationDetailDto);
		
		List<Order> ordersInOrderPreparationDetails= orderPreparationDetailService.findAll().stream().map(orderPreparationDetail -> orderPreparationDetail.getOrder()).toList();
		List<Order> orders = orderService.findAll();
		orders.removeAll(ordersInOrderPreparationDetails);
		model.addAttribute("orders", orders);
		
		return "owner-create-order-preparation-detail.html";
	}
	
	@PostMapping(value = "/owner/order-preparation-details/{employeeCode}/{orderCode}", params = "delete")
	public String deletePurchaseOrderDetail(@PathVariable(name = "employeeCode") String employeeCode, 
			@PathVariable("orderCode") String orderCode, RedirectAttributes redirectAttributes, Model model) {
		OrderPreparationDetailPK orderPreparationDetailPK = new OrderPreparationDetailPK();
		orderPreparationDetailPK.setEmployeeCode(employeeCode);
		orderPreparationDetailPK.setOrderCode(orderCode);
		orderPreparationDetailService.deleteByOrderPreparationDetailPK(orderPreparationDetailPK);
		redirectAttributes.addFlashAttribute("isDeletedOrderPreparationDetail", true);
		model.addAttribute("employeeCode", employeeCode);
		return "redirect:/owner/order-preparation-details?employee-code=" + employeeCode;
	}
	
	@GetMapping(value = "/owner/order-preparation-details", params = {"employee-code", "page-number"})
	public String orderPreparationDetailPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("employee-code") String employeeCode, Model model) {
		List<OrderPreparationDetail> orderPreparationDetails = orderPreparationDetailService.findAll().stream().filter(orderPreparationDetail -> orderPreparationDetail.getOrderPreparationDetailPK().getEmployeeCode().equals(employeeCode)).toList();
		model.addAttribute("orderPreparationDetails", orderPreparationDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("employeeCode", employeeCode);
		
		return "owner-order-preparation-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/order-preparation-details", params = {"employee-code", "export"})
	@ResponseBody
	public List<OrderPreparationDetailDto> getOrderPreparationDetailDtos(@RequestParam("employee-code") String employeeCode) {
		List<OrderPreparationDetailDto> orderPreparationDetailDtos = OrderPreparationDetailMapper.toPreparationDetailDtos(orderPreparationDetailService.findByEmployee(employeeService.findByEmployeeCode(employeeCode).get()));
		return orderPreparationDetailDtos;
	}
}

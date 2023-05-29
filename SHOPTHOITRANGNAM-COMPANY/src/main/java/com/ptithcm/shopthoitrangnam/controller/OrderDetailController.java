package com.ptithcm.shopthoitrangnam.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.OrderDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.OrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.mapper.OrderDetailMapper;
import com.ptithcm.shopthoitrangnam.service.OrderDetailService;
import com.ptithcm.shopthoitrangnam.service.OrderService;

@Controller
public class OrderDetailController {
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value = "/owner/order-details", params = "order-code")
	public String orderDetailPage(Model model, @RequestParam("order-code") String orderCode, RedirectAttributes redirectAttributes) {
		List<OrderDetail> orderDetails = orderDetailService.findByOrder(orderService.findByOrderCode(orderCode).get());
		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("orderCode", orderCode);
		return "owner-order-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/order-details", params = {"order-code", "search"})
	public String searchOrderDetail(Model model, @RequestParam("order-code") String orderCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/order-details?order-code=" + orderCode;
		}
		
		Set<OrderDetail> orderDetails = new LinkedHashSet<>();
		search = search.trim();
		if (NumberUtils.isCreatable(search)) {
			OrderDetailPK orderDetailPK = new OrderDetailPK();
			orderDetailPK.setOrderCode(orderCode);
			orderDetailPK.setProductDetailId(NumberUtils.createInteger(search));
			Optional<OrderDetail> orderDetail = orderDetailService.findByOrderDetailPK(orderDetailPK);
			if (orderDetail.isPresent()) {
				orderDetails.add(orderDetail.get());
			}
		}
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<OrderDetail> orderDetailsByProductName = orderDetailService.findByOrder(orderService.findByOrderCode(orderCode).get()).stream().filter(orderDetail -> orderDetail.getProductDetail().getProduct().getProductName().matches(pattern)).toList();
		orderDetails.addAll(orderDetailsByProductName);
		 
		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("orderCode", orderCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-order-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/order-details", params = {"order-code", "page-number"})
	public String orderPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("order-code") String orderCode, Model model) {
		List<OrderDetail> orderDetails = orderDetailService.findByOrder(orderService.findByOrderCode(orderCode).get());
		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("orderCode", orderCode);
		return "owner-order-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/order-details", params = {"order-code", "export"})
	@ResponseBody
	public List<OrderDetailDto> getSupplierDtos(@RequestParam("order-code") String orderCode) {
		List<OrderDetailDto> orderDetailDtos = OrderDetailMapper.toOrderDetailDtos(orderDetailService.findAll().stream().filter(orderDetail -> orderDetail.getOrderDetailPK().getOrderCode().equals(orderCode)).toList());
		return orderDetailDtos;
	}
}

package com.ptithcm.shopthoitrangnam.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.OrderDto;
import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;
import com.ptithcm.shopthoitrangnam.entity.Order;
import com.ptithcm.shopthoitrangnam.entity.OrderStatusDetail;
import com.ptithcm.shopthoitrangnam.enumeration.OrderStatus;
import com.ptithcm.shopthoitrangnam.mapper.OrderMapper;
import com.ptithcm.shopthoitrangnam.service.DeliveryNoteService;
import com.ptithcm.shopthoitrangnam.service.EmployeeService;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.OrderService;
import com.ptithcm.shopthoitrangnam.service.OrderStatusDetailService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Autowired
	OrderStatusDetailService orderStatusDetailService;
	
	@Autowired
	DeliveryNoteService deliveryNoteService;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value = "/owner/orders")
	public String orderPage(Model model, RedirectAttributes redirectAttributes) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		model.addAttribute("pageNumber", 0);
		Boolean isUpdateStatusOrder = (Boolean) redirectAttributes.getFlashAttributes().get("isUpdateStatusOrder");
		if (isUpdateStatusOrder != null) {
			model.addAttribute("isUpdateStatusOrder", isUpdateStatusOrder);
		}
		
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		
		Integer numberOfOrdersAwaitingConfirmation = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.WAITING.getCode())).toList().size();
		Integer numberOfOrdersConfirmed = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CONFIRMED.getCode())).toList().size();
		Integer numberOfOrdersInTransit = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.TRANSPORT.getCode())).toList().size();
		Integer numberOfOrdersCompleted = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.COMPLETED.getCode())).toList().size();
		Integer numberOfOrdersCancelled = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CANCELLED.getCode())).toList().size();
		model.addAttribute("numberOfOrdersAwaitingConfirmation", numberOfOrdersAwaitingConfirmation);
		model.addAttribute("numberOfOrdersConfirmed", numberOfOrdersConfirmed);
		model.addAttribute("numberOfOrdersInTransit", numberOfOrdersInTransit);
		model.addAttribute("numberOfOrdersCompleted", numberOfOrdersCompleted);
		model.addAttribute("numberOfOrdersCancelled", numberOfOrdersCancelled);
		
		return "owner-order-manage.html";
	}
	
	@GetMapping(value = "/owner/orders", params = {"delivery-note-code"})
	public String deliveryNoteDetailPage(Model model, @RequestParam("delivery-note-code") String deliveryNoteCode, RedirectAttributes redirectAttributes) {
		List<Order> orders = orderService.findByDeliveryNote(deliveryNoteService.findByDeliveryNoteCode(deliveryNoteCode).get());
		model.addAttribute("orders", orders);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("deliveryNoteCode", deliveryNoteCode);
		
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		
		return "owner-delivery-note-detail.html";
	}
	
	@GetMapping(value = "/owner/orders", params = {"delivery-staff-code"})
	public String orderOfShipperPage(Model model, @RequestParam("delivery-staff-code") String deliveryStaffCode, RedirectAttributes redirectAttributes) {
		List<DeliveryNote> deliveryNotes = deliveryNoteService.findByDeliveryStaff(employeeService.findByEmployeeCode(deliveryStaffCode).get());
		Set<Order> orders = new LinkedHashSet<>();
		deliveryNotes.forEach(deliveryNote -> {
			orders.addAll(deliveryNote.getOrders());
		});
		model.addAttribute("orders", orders);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("deliveryStaffCode", deliveryStaffCode);
		
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		
		return "owner-order-of-shipper.html";
	}
	
	@GetMapping(value = "/owner/orders", params = "search")
	public String searchOrder(Model model, @RequestParam("search") String search) {
		Integer numberOfOrdersAwaitingConfirmation = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatusDetailPK().getOrderCode().equals(OrderStatus.WAITING.getCode())).toList().size();
		Integer numberOfOrdersConfirmed = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CONFIRMED.getCode())).toList().size();
		Integer numberOfOrdersInTransit = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.TRANSPORT.getCode())).toList().size();
		Integer numberOfOrdersCompleted = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.COMPLETED.getCode())).toList().size();
		Integer numberOfOrdersCancelled = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CANCELLED.getCode())).toList().size();
		model.addAttribute("numberOfOrdersAwaitingConfirmation", numberOfOrdersAwaitingConfirmation);
		model.addAttribute("numberOfOrdersConfirmed", numberOfOrdersConfirmed);
		model.addAttribute("numberOfOrdersInTransit", numberOfOrdersInTransit);
		model.addAttribute("numberOfOrdersCompleted", numberOfOrdersCompleted);
		model.addAttribute("numberOfOrdersCancelled", numberOfOrdersCancelled);
		
		if (search.isEmpty()) {
			return "redirect:/owner/orders";
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<Order> orders = orderService.findByOrderCodeUsingRegex(pattern);
		 
		model.addAttribute("orders", orders);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		
		return "owner-order-manage.html";
	}
	
	@GetMapping(value = "/owner/orders", params = {"delivery-note-code", "search"})
	public String searchOrderFromDeliveryNoteDetail(Model model, @RequestParam("delivery-note-code") String deliveryNoteCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/orders?delivery-note-code=" + deliveryNoteCode;
		}
		
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<Order> orders = orderService.findByDeliveryNote(deliveryNoteService.findByDeliveryNoteCode(deliveryNoteCode).get()).stream().filter(order -> order.getOrderCode().matches(pattern)).toList();
		 
		model.addAttribute("orders", orders);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("deliveryNoteCode", deliveryNoteCode);
		
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		
		return "owner-delivery-note-detail.html";
	}
	
	@GetMapping(value = "/owner/orders", params = {"delivery-staff-code", "search"})
	public String searchOrderOfShipper(Model model, @RequestParam("delivery-staff-code") String deliveryStaffCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/orders?delivery-staff-code=" + deliveryStaffCode;
		}
		
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<DeliveryNote> deliveryNotes = deliveryNoteService.findByDeliveryStaff(employeeService.findByEmployeeCode(deliveryStaffCode).get());
		Set<Order> orders = new LinkedHashSet<>();
		deliveryNotes.forEach(deliveryNote -> {
			orders.addAll(deliveryNote.getOrders().stream().filter(order -> order.getOrderCode().matches(pattern)).toList());
		});
		 
		model.addAttribute("orders", orders);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("deliveryStaffCode", deliveryStaffCode);
		
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		
		return "owner-order-of-shipper.html";
	}
	
	@GetMapping(value = "/owner/orders", params = "status")
	public String filterByStatusOrder(Model model, @RequestParam("status") String status) {
		Integer numberOfOrdersAwaitingConfirmation = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.WAITING.getCode())).toList().size();
		Integer numberOfOrdersConfirmed = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CONFIRMED.getCode())).toList().size();
		Integer numberOfOrdersInTransit = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.TRANSPORT.getCode())).toList().size();
		Integer numberOfOrdersCompleted = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.COMPLETED.getCode())).toList().size();
		Integer numberOfOrdersCancelled = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CANCELLED.getCode())).toList().size();
		model.addAttribute("numberOfOrdersAwaitingConfirmation", numberOfOrdersAwaitingConfirmation);
		model.addAttribute("numberOfOrdersConfirmed", numberOfOrdersConfirmed);
		model.addAttribute("numberOfOrdersInTransit", numberOfOrdersInTransit);
		model.addAttribute("numberOfOrdersCompleted", numberOfOrdersCompleted);
		model.addAttribute("numberOfOrdersCancelled", numberOfOrdersCancelled);
		List<Order> orders = orderService.findAll().stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(status)).toList();
		model.addAttribute("orders", orders);
		model.addAttribute("pageNumber", 0);
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		return "owner-order-manage.html";
	}
	
	@PostMapping(value = "/owner/orders/{orderCode}", params = {"confirmation"})
	public String updateStatusOrder(Model model, @PathVariable("orderCode") String orderCode, RedirectAttributes redirectAttributes) {
		orderStatusDetailService.insert(orderCode, OrderStatus.CONFIRMED.getCode());
		redirectAttributes.addFlashAttribute("isUpdateStatusOrder", true);
		return "redirect:/owner/orders"; 
	}
	
	@GetMapping(value = "/owner/orders", params = "page-number")
	public String orderPageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		model.addAttribute("pageNumber", pageNumber-1);
		
		List<String> statuses = new ArrayList<>();
		orders.forEach(order -> {
			statuses.add(order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode());
		});
		model.addAttribute("statuses", statuses);
		
		Integer numberOfOrdersAwaitingConfirmation = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.WAITING.getCode())).toList().size();
		Integer numberOfOrdersConfirmed = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CONFIRMED.getCode())).toList().size();
		Integer numberOfOrdersInTransit = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.TRANSPORT.getCode())).toList().size();
		Integer numberOfOrdersCompleted = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.COMPLETED.getCode())).toList().size();
		Integer numberOfOrdersCancelled = orders.stream().filter(order -> order.getOrderStatusDetails().stream().sorted(new OrderStatusDetailComparator()).findFirst().get().getOrderStatus().getOrderStatusCode().equals(OrderStatus.CANCELLED.getCode())).toList().size();
		model.addAttribute("numberOfOrdersAwaitingConfirmation", numberOfOrdersAwaitingConfirmation);
		model.addAttribute("numberOfOrdersConfirmed", numberOfOrdersConfirmed);
		model.addAttribute("numberOfOrdersInTransit", numberOfOrdersInTransit);
		model.addAttribute("numberOfOrdersCompleted", numberOfOrdersCompleted);
		model.addAttribute("numberOfOrdersCancelled", numberOfOrdersCancelled);
		
		return "owner-order-manage.html";
	}
	
	@GetMapping(value = "/owner/orders", params = "export")
	@ResponseBody
	public List<OrderDto> getOrderDtos() {
		List<OrderDto> orderDtos = OrderMapper.toOrderDtos(orderService.findAll());
		return orderDtos;
	}
	
	@GetMapping(value = "/owner/orders", params = {"delivery-note-code", "export"})
	@ResponseBody
	public List<OrderDto> getOrderDtos(@RequestParam("delivery-note-code") String deliveryNoteCode) {
		List<OrderDto> orderDtos = OrderMapper.toOrderDtos(orderService.findByDeliveryNote(deliveryNoteService.findByDeliveryNoteCode(deliveryNoteCode).get()));
		return orderDtos;
	}
	
	@GetMapping(value = "/owner/orders", params = {"delivery-staff-code", "export"})
	@ResponseBody
	public List<OrderDto> getOrderDtosOfShipper(@RequestParam("delivery-staff-code") String deliveryStaffCode) {
		List<DeliveryNote> deliveryNotes = deliveryNoteService.findByDeliveryStaff(employeeService.findByEmployeeCode(deliveryStaffCode).get());
		List<Order> orders = new ArrayList<>();
		deliveryNotes.forEach(deliveryNote -> {
			orders.addAll(deliveryNote.getOrders());
		});
		List<OrderDto> orderDtos = OrderMapper.toOrderDtos(orders);
		return orderDtos;
	}
	
	private class OrderStatusDetailComparator implements Comparator<OrderStatusDetail> {
		@Override
		public int compare(OrderStatusDetail o1, OrderStatusDetail o2) {
			Long transitionTime1 = o1.getOrderStatusDetailPK().getTransitionTime().getTime();
			Long transitionTime2 = o2.getOrderStatusDetailPK().getTransitionTime().getTime();
			return transitionTime2.compareTo(transitionTime1);
		}
	}
	
//	public BigDecimal totalOrderAmount(Order order) {
//		List<BigDecimal> totalOrderDetailAmounts = new ArrayList<>();
//		for (OrderDetail orderDetail : order.getOrderDetails()) {
//			Optional<FlatRateSale> flatRateSale = flatRateSaleService.findAll().stream().filter(flatRateSale1 -> order.getCreationTime().getTime() >= flatRateSale1.getStartTime().getTime() && order.getCreationTime().getTime() <= flatRateSale1.getEndTime().getTime() && flatRateSale1.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flatRateSaleDetail.getFlatRateSaleDetailPK().getProductDetailId().equals(orderDetail.getOrderDetailPK().getProductDetailId()))).findFirst();
//			if (flatRateSale.isPresent()) {
//				totalOrderDetailAmounts.add(flatRateSale.get().getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
//			} else {
//				BigDecimal originalPrice = orderDetail.getProductDetail().getSellingPrices().stream().filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= order.getCreationTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get();
//				Optional<FlashSale> flashSale = flashSaleService.findAll().stream().filter(flashSale1 -> order.getCreationTime().getTime() >= flashSale1.getStartTime().getTime() && order.getCreationTime().getTime() <= flashSale1.getEndTime().getTime() && flashSale1.getFlashSaleDetails().stream().anyMatch(flashSaleDetail -> flashSaleDetail.getFlashSaleDetailPK().getProductDetailId().equals(orderDetail.getOrderDetailPK().getProductDetailId()))).findFirst();
//				if (flashSale.isPresent()) {
//					FlashSaleDetail flashSaleDetail = flashSale.get().getFlashSaleDetails().stream().filter(flashSaleDetail -> flashSaleDetail.getFlashSaleDetailPK().getProductDetailId().equals(orderDetail.getOrderDetailPK().getProductDetailId())).findFirst().get();
//					Float flashSalePercentage = flashSaleDetail.getFlashSalePercentage();
//					Integer restQuantity = flashSaleDetail.getLimitedQuantity() - orderService.findAll().stream().filter(order1 -> order1.getCreationTime().getTime() >= flashSale.get().getStartTime() && order1.getCreationTime().getTime() <= order.getCreationTime().getTime() && order1.getOrderDetails().stream().anyMatch(orderDetail1 -> orderDetail1.getOrderDetailPK().getProductDetailId().equals(orderDetail.getOrderDetailPK().getProductDetailId()))).map(order2 -> order2.getOrderDetails().stream().filter(orderDetail2 -> orderDetail2.getOrderDetailPK().getProductDetailId().equals(orderDetail.getOrderDetailPK().getProductDetailId())).findFirst().get()).reduce(0, (odt1, odt2) -> odt1.getQuantity() + odt2.getQuantity());
//				} else {
//					
//				}
//			}
//		}
//	}
}

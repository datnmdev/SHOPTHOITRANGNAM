package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.OrderDto;
import com.ptithcm.shopthoitrangnam.entity.Order;

public class OrderMapper {
	public static OrderDto toOrderDto(Order order) {
		return new OrderDto(order.getOrderCode(), order.getCreationTime(), order.getAddressUpdateHistory().getAddressUpdateHistoryId(), order.getCustomer().getCustomerCode(), order.getDeliveryNote() != null ? order.getDeliveryNote().getDeliveryNoteCode() : null);
	}
	
	public static List<OrderDto> toOrderDtos(List<Order> orders) {
		return orders.stream().map(order -> toOrderDto(order)).toList();
	}
}

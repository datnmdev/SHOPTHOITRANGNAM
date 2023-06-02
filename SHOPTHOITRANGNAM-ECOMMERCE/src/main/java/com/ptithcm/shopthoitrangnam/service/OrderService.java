package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;
import com.ptithcm.shopthoitrangnam.entity.Order;

public interface OrderService {
	public List<Order> findAll();
	
	public Optional<Order> findByOrderCode(String orderCode);
	
	public List<Order> findByDeliveryNote(DeliveryNote deliveryNote);
	
	public List<Order> findByOrderCodeUsingRegex(String pattern);
}

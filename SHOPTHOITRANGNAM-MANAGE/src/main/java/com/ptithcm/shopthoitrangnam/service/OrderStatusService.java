package com.ptithcm.shopthoitrangnam.service;

import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.OrderStatus;

public interface OrderStatusService {
	public Optional<OrderStatus> findByOrderStatusCode(String orderStatusCode);
}

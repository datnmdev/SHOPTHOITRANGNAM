package com.ptithcm.shopthoitrangnam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.OrderStatus;
import com.ptithcm.shopthoitrangnam.repository.OrderStatusRepository;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
	@Autowired
	OrderStatusRepository orderStatusRepository;
	
	@Override
	public Optional<OrderStatus> findByOrderStatusCode(String orderStatusCode) {
		return orderStatusRepository.findByOrderStatusCode(orderStatusCode);
	}
}

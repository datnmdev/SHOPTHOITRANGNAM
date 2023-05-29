package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.Order;
import com.ptithcm.shopthoitrangnam.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	@Override
	public Optional<Order> findByOrderCode(String orderCode) {
		return orderRepository.findByOrderCode(orderCode);
	}
	
	@Override
	public List<Order> findByOrderCodeUsingRegex(String pattern) {
		return orderRepository.findByOrderCodeUsingRegex(pattern);
	}
}

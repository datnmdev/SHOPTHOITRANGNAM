package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.embeddable.OrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.Order;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}
	
	@Override
	public Optional<OrderDetail> findByOrderDetailPK(OrderDetailPK orderDetailPK) {
		return orderDetailRepository.findByOrderDetailPK(orderDetailPK);
	}
	
	@Override
	public List<OrderDetail> findByOrder(Order order) {
		return orderDetailRepository.findByOrder(order);
	}
}

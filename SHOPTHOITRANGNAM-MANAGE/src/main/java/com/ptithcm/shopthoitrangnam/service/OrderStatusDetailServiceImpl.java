package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.embeddable.OrderStatusDetailPK;
import com.ptithcm.shopthoitrangnam.entity.OrderStatusDetail;
import com.ptithcm.shopthoitrangnam.repository.OrderStatusDetailRepository;

@Service
public class OrderStatusDetailServiceImpl implements OrderStatusDetailService {
	@Autowired
	OrderStatusDetailRepository orderStatusDetailRepository;
	
	@Autowired
	OrderStatusService orderStatusService;
	
	@Override
	public void insert(String orderCode, String orderStatusCode) {
		OrderStatusDetail orderStatusDetail = new OrderStatusDetail();
		OrderStatusDetailPK orderStatusDetailPK = new OrderStatusDetailPK();
		orderStatusDetailPK.setOrderCode(orderCode);
		orderStatusDetailPK.setTransitionTime(new Date());
		orderStatusDetail.setOrderStatusDetailPK(orderStatusDetailPK);
		orderStatusDetail.setOrderStatus(orderStatusService.findByOrderStatusCode(orderStatusCode).get());
		
		orderStatusDetailRepository.save(orderStatusDetail);
	}
}

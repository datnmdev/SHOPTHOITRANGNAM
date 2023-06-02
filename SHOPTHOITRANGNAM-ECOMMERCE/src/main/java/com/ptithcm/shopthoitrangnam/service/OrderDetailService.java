package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.embeddable.OrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.Order;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;

public interface OrderDetailService {
	public List<OrderDetail> findAll();
	
	public Optional<OrderDetail> findByOrderDetailPK(OrderDetailPK orderDetailPK);
	
	public List<OrderDetail> findByOrder(Order order);
	
	public List<OrderDetail> findByProductDetail(ProductDetail productDetail);
}

package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.OrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.Order;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
	public List<OrderDetail> findAll();
	
	public Optional<OrderDetail> findByOrderDetailPK(OrderDetailPK orderDetailPK);
	
	public List<OrderDetail> findByOrder(Order order);
	
	public List<OrderDetail> findByProductDetail(ProductDetail productDetail);
}

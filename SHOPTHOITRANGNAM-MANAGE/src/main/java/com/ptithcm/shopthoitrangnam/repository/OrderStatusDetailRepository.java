package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.OrderStatusDetailPK;
import com.ptithcm.shopthoitrangnam.entity.OrderStatusDetail;

@Repository
public interface OrderStatusDetailRepository extends JpaRepository<OrderStatusDetail, OrderStatusDetailPK> {
	public OrderStatusDetail save(OrderStatusDetail orderStatusDetail);
}

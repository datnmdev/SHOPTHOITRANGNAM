package com.ptithcm.shopthoitrangnam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {
	public Optional<OrderStatus> findByOrderStatusCode(String orderStatusCode);
}

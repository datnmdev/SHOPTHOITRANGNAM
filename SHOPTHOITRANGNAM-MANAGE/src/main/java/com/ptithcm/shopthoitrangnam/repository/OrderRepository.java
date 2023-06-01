package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;
import com.ptithcm.shopthoitrangnam.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
	public List<Order> findAll();
	
	public Optional<Order> findByOrderCode(String orderCode);
	
	public List<Order> findByDeliveryNote(DeliveryNote deliveryNote);
	
	@Query(value = "SELECT * FROM DONHANG WHERE MADONHANG LIKE :pattern", nativeQuery = true)
	public List<Order> findByOrderCodeUsingRegex(@Param("pattern") String pattern);
}

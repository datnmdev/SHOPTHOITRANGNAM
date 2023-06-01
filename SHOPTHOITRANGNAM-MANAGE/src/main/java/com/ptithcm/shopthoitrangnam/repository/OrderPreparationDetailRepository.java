package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.OrderPreparationDetailPK;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.entity.OrderPreparationDetail;

@Repository
public interface OrderPreparationDetailRepository extends JpaRepository<OrderPreparationDetail, OrderPreparationDetailPK> {
	public List<OrderPreparationDetail> findAll();
	
	public Optional<OrderPreparationDetail> findByOrderPreparationDetailPK(OrderPreparationDetailPK orderPreparationDetailPK);
	
	public List<OrderPreparationDetail> findByEmployee(Employee employee);
	
	public void deleteByOrderPreparationDetailPK(OrderPreparationDetailPK orderPreparationDetailPK);
	
	public OrderPreparationDetail save(OrderPreparationDetail orderPreparationDetail);
}

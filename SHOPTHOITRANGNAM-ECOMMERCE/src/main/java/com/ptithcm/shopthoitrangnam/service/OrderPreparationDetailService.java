package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.OrderPreparationDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.OrderPreparationDetailPK;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.entity.OrderPreparationDetail;

public interface OrderPreparationDetailService {
	public List<OrderPreparationDetail> findAll();
	
	public Optional<OrderPreparationDetail> findByOrderPreparationDetailPK(OrderPreparationDetailPK orderPreparationDetailPK);
	
	public List<OrderPreparationDetail> findByEmployee(Employee employee);
	
	public void deleteByOrderPreparationDetailPK(OrderPreparationDetailPK orderPreparationDetailPK);
	
	public void save(OrderPreparationDetailDto orderPreparationDetailDto);
}

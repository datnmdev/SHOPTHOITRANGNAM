package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.OrderPreparationDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.OrderPreparationDetailPK;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.entity.OrderPreparationDetail;
import com.ptithcm.shopthoitrangnam.repository.OrderPreparationDetailRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderPreparationDetailServiceImpl implements OrderPreparationDetailService {
	@Autowired
	OrderPreparationDetailRepository orderPreparationDetailRepository;
	
	@Override
	public List<OrderPreparationDetail> findAll() {
		return orderPreparationDetailRepository.findAll();
	}
	
	@Override
	public Optional<OrderPreparationDetail> findByOrderPreparationDetailPK(
			OrderPreparationDetailPK orderPreparationDetailPK) {
		return orderPreparationDetailRepository.findByOrderPreparationDetailPK(orderPreparationDetailPK);
	}
	
	@Override
	public List<OrderPreparationDetail> findByEmployee(Employee employee) {
		return orderPreparationDetailRepository.findByEmployee(employee);
	}
	
	@Override
	public void deleteByOrderPreparationDetailPK(OrderPreparationDetailPK orderPreparationDetailPK) {
		orderPreparationDetailRepository.deleteByOrderPreparationDetailPK(orderPreparationDetailPK);
	}
	
	@Override
	public void save(OrderPreparationDetailDto orderPreparationDetailDto) {
		OrderPreparationDetail orderPreparationDetail = new OrderPreparationDetail();
		OrderPreparationDetailPK orderPreparationDetailPK = new OrderPreparationDetailPK();
		orderPreparationDetailPK.setEmployeeCode(orderPreparationDetailDto.getEmployeeCode());
		orderPreparationDetailPK.setOrderCode(orderPreparationDetailDto.getOrderCode());
		orderPreparationDetail.setOrderPreparationDetailPK(orderPreparationDetailPK);
		orderPreparationDetail.setTaskDeliveryTime(new Date());
		orderPreparationDetailRepository.save(orderPreparationDetail);
	}
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.DeliveryNoteDto;
import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.repository.DeliveryNoteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeliveryNoteServiceImpl implements DeliveryNoteService {
	@Autowired
	DeliveryNoteRepository deliveryNoteRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Override
	public List<DeliveryNote> findAll() {
		return deliveryNoteRepository.findAll();
	}
	
	@Override
	public Optional<DeliveryNote> findByDeliveryNoteCode(String deliveryNoteCode) {
		return deliveryNoteRepository.findByDeliveryNoteCode(deliveryNoteCode);
	}
	
	@Override
	public List<DeliveryNote> findByEmployee(Employee employee) {
		return deliveryNoteRepository.findByEmployee(employee);
	}
	
	@Override
	public List<DeliveryNote> findByDeliveryStaff(Employee employee) {
		return deliveryNoteRepository.findByDeliveryStaff(employee);
	}
	
	@Override
	public List<DeliveryNote> findByDeliveryNoteCodeUsingRegex(String pattern) {
		return deliveryNoteRepository.findByDeliveryNoteCodeUsingRegex(pattern);
	}
	
	@Override
	public List<DeliveryNote> findByDeliveryNoteNameUsingRegex(String pattern) {
		return deliveryNoteRepository.findByDeliveryNoteNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByDeliveryNoteCode(String purchaseOrderCode) {
		deliveryNoteRepository.deleteByDeliveryNoteCode(purchaseOrderCode);
	}
	
	@Override
	public void save(DeliveryNoteDto deliveryNoteDto) {
		DeliveryNote deliveryNote = new DeliveryNote();
		deliveryNote.setDeliveryNoteCode(deliveryNoteDto.getDeliveryNoteCode());
		deliveryNote.setDeliveryNoteName(deliveryNoteDto.getDeliveryNoteName());
		deliveryNote.setDescription(deliveryNoteDto.getDescription());
		deliveryNote.setCreationTime(new Date());
		deliveryNote.setEmployee(employeeService.findByEmployeeCode(deliveryNoteDto.getEmployeeCode()).get());
		deliveryNote.setDeliveryStaff(employeeService.findByEmployeeCode(deliveryNoteDto.getDeliveryStaffCode()).get());
		deliveryNoteRepository.save(deliveryNote);
	}
}

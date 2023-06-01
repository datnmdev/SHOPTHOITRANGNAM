package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.DeliveryNoteDto;
import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;
import com.ptithcm.shopthoitrangnam.entity.Employee;

public interface DeliveryNoteService {
	public List<DeliveryNote> findAll();
	
	public Optional<DeliveryNote> findByDeliveryNoteCode(String deliveryNoteCode);
	
	public List<DeliveryNote> findByEmployee(Employee employee);
	
	public List<DeliveryNote> findByDeliveryStaff(Employee employee);
	
	public List<DeliveryNote> findByDeliveryNoteCodeUsingRegex(String pattern);
	
	public List<DeliveryNote> findByDeliveryNoteNameUsingRegex(String pattern);
	
	public void deleteByDeliveryNoteCode(String purchaseOrderCode);
	
	public void save(DeliveryNoteDto deliveryNoteDto);
}

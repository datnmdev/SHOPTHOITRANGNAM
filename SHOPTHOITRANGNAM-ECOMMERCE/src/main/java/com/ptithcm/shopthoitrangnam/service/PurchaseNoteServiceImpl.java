package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDto;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;
import com.ptithcm.shopthoitrangnam.repository.PurchaseNoteRepository;

@Service
public class PurchaseNoteServiceImpl implements PurchaseNoteService {
	@Autowired
	PurchaseNoteRepository purchaseNoteRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Override
	public List<PurchaseNote> findAll() {
		return purchaseNoteRepository.findAll();
	}
	
	@Override
	public Optional<PurchaseNote> findByPurchaseNoteCode(String purchaseNoteCode) {
		return purchaseNoteRepository.findByPurchaseNoteCode(purchaseNoteCode);
	}
	
	@Override
	public List<PurchaseNote> findByEmployee(Employee employee) {
		return purchaseNoteRepository.findByEmployee(employee);
	}
	
	@Override
	public List<PurchaseNote> findByPurchaseNoteCodeUsingRegex(String pattern) {
		return purchaseNoteRepository.findByPurchaseNoteCodeUsingRegex(pattern);
	}
	
	@Override
	public List<PurchaseNote> findByPurchaseNoteNameUsingRegex(String pattern) {
		return purchaseNoteRepository.findByPurchaseNoteNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByPurchaseNoteCode(String purchaseNoteCode) {
		purchaseNoteRepository.deleteByPurchaseNoteCode(purchaseNoteCode);
	}
	
	@Override
	public void save(PurchaseNoteDto purchaseNoteDto) {
		PurchaseNote purchaseNote = new PurchaseNote();
		purchaseNote.setPurchaseNoteCode(purchaseNoteDto.getPurchaseNoteCode());
		purchaseNote.setPurchaseNoteName(purchaseNoteDto.getPurchaseNoteName());
		purchaseNote.setDescription(purchaseNoteDto.getDescription());
		purchaseNote.setCreationTime(new Date());
		purchaseNote.setEmployee(employeeService.findByEmployeeCode(purchaseNoteDto.getEmployeeCode()).get());
		purchaseNote.setPurchaseOrder(purchaseOrderService.findByPurchaseOrderCode(purchaseNoteDto.getPurchaseOrderCode()).get());
		purchaseNoteRepository.save(purchaseNote);
	}
}

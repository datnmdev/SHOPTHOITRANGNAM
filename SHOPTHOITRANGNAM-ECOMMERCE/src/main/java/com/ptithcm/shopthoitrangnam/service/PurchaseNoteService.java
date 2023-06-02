package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDto;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;

public interface PurchaseNoteService {
	public List<PurchaseNote> findAll();
	
	public Optional<PurchaseNote> findByPurchaseNoteCode(String purchaseNoteCode);
	
	public List<PurchaseNote> findByEmployee(Employee employee);
	
	public List<PurchaseNote> findByPurchaseNoteCodeUsingRegex(String pattern);
	
	public List<PurchaseNote> findByPurchaseNoteNameUsingRegex(String pattern);
	
	public void deleteByPurchaseNoteCode(String purchaseNoteCode);
	
	public void save(PurchaseNoteDto purchaseNoteDto);
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.SupplierDto;
import com.ptithcm.shopthoitrangnam.entity.Supplier;

public interface SupplierService {
	public List<Supplier> findAll();
	
	public Optional<Supplier> findBySupplierCode(String supplierCode); 
	
	public List<Supplier> findBySupplierCodeUsingRegex(String pattern);
	
	public List<Supplier> findBySupplierNameUsingRegex(String pattern);
	
	public void deleteBySupplierCode(String supplierCode);
	
	public void save(SupplierDto supplierDto);
}

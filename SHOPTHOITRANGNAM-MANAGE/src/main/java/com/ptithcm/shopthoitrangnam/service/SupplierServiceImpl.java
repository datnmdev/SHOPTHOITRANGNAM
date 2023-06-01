package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.SupplierDto;
import com.ptithcm.shopthoitrangnam.entity.Supplier;
import com.ptithcm.shopthoitrangnam.enumeration.ContractStatus;
import com.ptithcm.shopthoitrangnam.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	SupplierRepository supplierRepository;
	
	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}
	
	@Override
	public Optional<Supplier> findBySupplierCode(String supplierCode) {
		return supplierRepository.findBySupplierCode(supplierCode);
	}
	
	@Override
	public List<Supplier> findBySupplierCodeUsingRegex(String pattern) {
		return supplierRepository.findBySupplierCodeUsingRegex(pattern);
	}
	
	@Override
	public List<Supplier> findBySupplierNameUsingRegex(String pattern) {
		return supplierRepository.findBySupplierNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteBySupplierCode(String supplierCode) {
		supplierRepository.deleteBySupplierCode(supplierCode);
	}
	
	@Override
	public void save(SupplierDto supplierDto) {
		Supplier supplier = new Supplier();
		supplier.setSupplierCode(supplierDto.getSupplierCode());
		supplier.setSupplierName(supplierDto.getSupplierName());
		supplier.setPhoneNumber(supplierDto.getPhoneNumber());
		supplier.setEmail(supplierDto.getEmail());
		supplier.setContractStatus(supplierDto.getContractStatus() ? ContractStatus.STILL_VALID : ContractStatus.EXPIRED);
		supplierRepository.save(supplier);
	}
}

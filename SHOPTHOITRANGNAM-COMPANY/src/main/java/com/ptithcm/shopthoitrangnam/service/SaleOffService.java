package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDto;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;

public interface SaleOffService {
	public List<SaleOff> findAll();
	
	public Optional<SaleOff> findBySaleOffId(Integer flashSaleId);
	
	public List<SaleOff> findBySaleOffNameUsingRegex(String pattern);
	
	public void deleteBySaleOffId(Integer saleOffId);
	
	public void save(SaleOffDto saleOffDto);
}

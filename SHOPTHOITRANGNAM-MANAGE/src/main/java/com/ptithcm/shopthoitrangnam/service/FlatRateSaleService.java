package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;

public interface FlatRateSaleService {
	public List<FlatRateSale> findAll();
	
	public Optional<FlatRateSale> findByFlatRateSaleId(Integer flatRateSaleId);
	
	public List<FlatRateSale> findByFlatRateSaleNameUsingRegex(String pattern);
	
	public void deleteByFlatRateSaleId(Integer flatRateSaleId);
	
	public void save(FlatRateSaleDto flatRateSaleDto);
}

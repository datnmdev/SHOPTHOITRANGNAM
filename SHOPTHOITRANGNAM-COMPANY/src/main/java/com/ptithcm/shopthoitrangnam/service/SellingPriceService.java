package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.SellingPriceDto;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;

public interface SellingPriceService {
	public List<SellingPrice> findAll();
	
	public Optional<SellingPrice> findBySellingPriceId(Integer sellingPriceId);
	
	public void deleteBySellingPriceId(Integer sellingPriceId);
	
	public void deleteAll(List<SellingPrice> sellingPrices);
	
	public void save(SellingPriceDto sellingPriceDto);
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;

public interface FlashSaleService {
	public List<FlashSale> findAll();
	
	public Optional<FlashSale> findByFlashSaleId(Integer flashSaleId);
	
	@Query(value = "SELECT * FROM FLASHSALE WHERE TENKHUYENMAI LIKE :pattern", nativeQuery = true)
	public List<FlashSale> findByFlashSaleNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByFlashSaleId(Integer flashSaleId);
	
	public void save(FlashSaleDto flashSaleDto);
}

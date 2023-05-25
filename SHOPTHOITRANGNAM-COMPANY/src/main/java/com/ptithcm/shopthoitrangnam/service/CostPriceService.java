package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.CostPriceDto;
import com.ptithcm.shopthoitrangnam.entity.CostPrice;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;

public interface CostPriceService {
	public List<CostPrice> findAll();
	
	public Optional<CostPrice> findByCostPriceId(Integer costPriceId);
	
	public List<CostPrice> findBySupplyDetail(SupplyDetail supplyDetail);
	
	public List<CostPrice> findBySupplyDetailAndEffectiveTime(SupplyDetail supplyDetail, Date effectiveTime);
	
	public void deleteByCostPriceId(Integer costPriceId);
	
	public void deleteAll(List<CostPrice> costPrices);
	
	public void save(CostPriceDto costPriceDto);	
}

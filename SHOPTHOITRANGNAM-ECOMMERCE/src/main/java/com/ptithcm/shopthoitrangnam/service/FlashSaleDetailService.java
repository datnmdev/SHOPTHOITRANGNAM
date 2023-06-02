package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.FlashSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;

public interface FlashSaleDetailService {
	public List<FlashSaleDetail> findAll();
	
	public Optional<FlashSaleDetail> findByFlashSaleDetailPK(FlashSaleDetailPK flashSaleDetailPK);
	
	public List<FlashSaleDetail> findByFlashSale(FlashSale flashSale);
	
	public void deleteByFlashSaleDetailPK(FlashSaleDetailPK flashSaleDetailPK);
	
	public void deleteAllByFlashSale(FlashSale flashSale);
	
	public void update(FlashSaleDetailDto flashSaleDetailDto);
	
	public void save(FlashSaleDetailDto flashSaleDetailDto);
}

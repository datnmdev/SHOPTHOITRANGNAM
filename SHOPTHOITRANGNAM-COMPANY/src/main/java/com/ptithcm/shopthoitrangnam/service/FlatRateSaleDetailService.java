package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.FlatRateSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;

public interface FlatRateSaleDetailService {
	public List<FlatRateSaleDetail> findAll();
	
	public Optional<FlatRateSaleDetail> findByFlatRateSaleDetailPK(FlatRateSaleDetailPK flatRateSaleDetailPK);
	
	public List<FlatRateSaleDetail> findByFlatRateSale(FlatRateSale flatRateSale);
	
	public void deleteByFlatRateSaleDetailPK(FlatRateSaleDetailPK flatRateSaleDetailPK);
	
	public void deleteAllByFlatRateSale(FlatRateSale flatRateSale);
	
	public void save(FlatRateSaleDetailDto flatRateSaleDetailDto);
}

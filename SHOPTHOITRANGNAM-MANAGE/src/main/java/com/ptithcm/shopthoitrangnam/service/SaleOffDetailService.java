package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.SaleOffDetailPK;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;

public interface SaleOffDetailService {
	public List<SaleOffDetail> findAll();
	
	public Optional<SaleOffDetail> findBySaleOffDetailPK(SaleOffDetailPK saleOffDetailPK);
	
	public List<SaleOffDetail> findBySaleOff(SaleOff saleOff);
	
	public void deleteBySaleOffDetailPK(SaleOffDetailPK saleOffDetailPK);
	
	public void deleteAllBySaleOff(SaleOff saleOff);
	
	public void update(SaleOffDetailDto saleOffDetailDto);
	
	public void save(SaleOffDetailDto saleOffDetailDto);
}

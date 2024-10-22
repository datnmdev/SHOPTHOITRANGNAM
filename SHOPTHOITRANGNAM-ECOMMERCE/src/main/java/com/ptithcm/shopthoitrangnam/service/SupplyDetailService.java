package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.SupplyDetailDto;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.Supplier;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;

public interface SupplyDetailService {
	public List<SupplyDetail> findAll();
	
	public Optional<SupplyDetail> findBySupplyDetailId(Integer supplyDetailId); 
	
	public List<SupplyDetail> findByProductDetail(ProductDetail productDetail);
	
	public List<SupplyDetail> findBySupplierAndProductDetail(Supplier supplier, ProductDetail productDetail);
	
	public void deleteBySupplyDetailId(Integer supplyDetailId);
	
	public void deleteBySupplier(Supplier supplier);
	
	public void insert(SupplyDetailDto supplyDetailDto);
}


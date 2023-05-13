package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.ProductCategory;

public interface ProductCategoryService {
	public List<ProductCategory> findAll();
	
	public Optional<ProductCategory> findByProductCategoryCode(String productCategoryCode);
}

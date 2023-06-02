package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.ProductCategoryDto;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;

public interface ProductCategoryService {
	public List<ProductCategory> findAll();
	
	public Optional<ProductCategory> findByProductCategoryCode(String productCategoryCode);
	
	public List<ProductCategory> findByProductCategoryCodeUsingRegex(String pattern);
	
	public List<ProductCategory> findByProductCategoryNameUsingRegex(String pattern);
	
	public void deleteByProductCategoryCode(String sizeCode);
	
	public void save(ProductCategoryDto productCategoryDto);
}

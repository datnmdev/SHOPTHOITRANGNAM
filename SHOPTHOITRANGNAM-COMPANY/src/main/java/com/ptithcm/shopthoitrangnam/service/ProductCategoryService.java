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
	
	@Query(value = "SELECT * FROM LOAISANPHAM WHERE MALOAISP LIKE :pattern", nativeQuery = true)
	public List<ProductCategory> findByProductCategoryCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM LOAISANPHAM WHERE MALOAISP LIKE :pattern", nativeQuery = true)
	public List<ProductCategory> findByProductCategoryNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByProductCategoryCode(String sizeCode);
	
	public void save(ProductCategoryDto productCategoryDto);
}

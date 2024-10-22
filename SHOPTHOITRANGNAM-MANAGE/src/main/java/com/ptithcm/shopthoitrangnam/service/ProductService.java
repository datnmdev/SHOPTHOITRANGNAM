package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ptithcm.shopthoitrangnam.dto.ProductDto;
import com.ptithcm.shopthoitrangnam.entity.Product;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;

public interface ProductService {
	public String getProductCode(String productTypeCode);
	
	public List<Product> findAll();
	
	public Optional<Product> findByProductCode(String productCode);
	
	public List<Product> findByProductCategory(ProductCategory productCategory);
	
	public List<Product> findByProductNameUsingRegex(String productName);
	
	public List<Product> findByProductCategoryCodeUsingRegex(String productCategoryCode);
	
	public void deleteByProductCode(String productCode);
	
	public void save(ProductDto productDto);
	
	public void save(String productCode ,ProductDto productDto);
}

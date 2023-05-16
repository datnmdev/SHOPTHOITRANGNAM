package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.ProductCategoryDto;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;
import com.ptithcm.shopthoitrangnam.repository.ProductCategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@Override
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}
	
	@Override
	public Optional<ProductCategory> findByProductCategoryCode(String productCategoryCode) {
		return productCategoryRepository.findByProductCategoryCode(productCategoryCode);
	}
	
	@Override
	public List<ProductCategory> findByProductCategoryCodeUsingRegex(String pattern) {
		return productCategoryRepository.findByProductCategoryCodeUsingRegex(pattern);
	}
	
	@Override
	public List<ProductCategory> findByProductCategoryNameUsingRegex(String pattern) {
		return productCategoryRepository.findByProductCategoryNameUsingRegex(pattern);
	}
	
	@Override
	@Transactional
	public void deleteByProductCategoryCode(String sizeCode) {
		productCategoryRepository.deleteByProductCategoryCode(sizeCode);
	}
	
	@Override
	@Transactional
	public void save(ProductCategoryDto productCategoryDto) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryCode(productCategoryDto.getProductCategoryCode());
		productCategory.setProductCategoryName(productCategoryDto.getProductCategoryName());
		
		productCategoryRepository.save(productCategory);
	}
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.ProductDto;
import com.ptithcm.shopthoitrangnam.entity.Product;
import com.ptithcm.shopthoitrangnam.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductCategoryService productCategoryService;
	
	@Override
	public String getProductCode(String productTypeCode) {
		return productRepository.getNextProductCode(productTypeCode);
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@Override
	public Page<Product> findAll(Pageable pageble) {
		return productRepository.findAll(pageble);
	}
	
	@Override
	public Optional<Product> findByProductCode(String productCode) {
		return productRepository.findByProductCode(productCode);
	}
	
	@Override
	public List<Product> findByProductNameUsingRegex(String productName) {
		return productRepository.findByProductNameUsingRegex(productName);
	}
	
	@Override
	public List<Product> findByProductCategoryCodeUsingRegex(String productCategoryCode) {
		return productRepository.findByProductCategoryCodeUsingRegex(productCategoryCode);
	}
	
	@Override
	@Transactional
	public void deleteByProductCode(String productCode) {
		productRepository.deleteByProductCode(productCode);
	}
	
	@Override
	public void save(ProductDto productDto) {
		Product product = new Product();
		product.setProductCode(getProductCode(productDto.getProductCategoryCode()));
		product.setProductName(productDto.getProductName());
		product.setDescription(productDto.getDescription());
		product.setProductCategory(productCategoryService.findByProductCategoryCode(productDto.getProductCategoryCode()).get());
		
		productRepository.save(product);
	}
	
	@Override
	public void save(String productCode, ProductDto productDto) {
		Product product = new Product();
		product.setProductCode(productCode);
		product.setProductName(productDto.getProductName());
		product.setDescription(productDto.getDescription());
		product.setProductCategory(productCategoryService.findByProductCategoryCode(productDto.getProductCategoryCode()).get());
		
		productRepository.save(product);
	}
}

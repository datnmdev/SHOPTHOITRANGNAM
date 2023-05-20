package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.ProductDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.ProductDetailPK;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;

public interface ProductDetailService {
	public List<ProductDetail> findAll();
	
	public Optional<ProductDetail> findByProductDetailId(Integer productDetailId);
	
	public Optional<ProductDetail> findByProductDetailPK(ProductDetailPK productDetailPK);
	
	public List<ProductDetail> findByProductCodeUsingRegex(String pattern);
	
	public List<ProductDetail> findBySizeCodeUsingRegex(String pattern);
	
	public List<ProductDetail> findByColorCodeUsingRegex(String pattern);
	
	public void deleteByProductDetailId(Integer productDetailId);
	
	public void insert(ProductDetailDto productDetailDto);
	
	public void update(ProductDetailDto productDetailDto);
}

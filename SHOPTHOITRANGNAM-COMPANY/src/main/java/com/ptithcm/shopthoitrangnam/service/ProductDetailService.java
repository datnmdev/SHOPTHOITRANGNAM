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
	
	@Query(value = "SELECT * FROM CHITIETSANPHAM WHERE MASP LIKE :pattern", nativeQuery = true)
	public List<ProductDetail> findByProductCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM CHITIETSANPHAM WHERE MAKICHTHUOC LIKE :pattern", nativeQuery = true)
	public List<ProductDetail> findBySizeCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM CHITIETSANPHAM WHERE MAMAUSAC LIKE :pattern", nativeQuery = true)
	public List<ProductDetail> findByColorCodeUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByProductDetailId(Integer productDetailId);
	
	public void insert(ProductDetailDto productDetailDto);
	
	public void update(ProductDetailDto productDetailDto);
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.ProductDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.ProductDetailPK;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.repository.ProductDetailRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductDetailServiceImpl implements ProductDetailService {
	@Autowired
	ProductDetailRepository productDetailRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ProductDetail> findAll() {
		return productDetailRepository.findAll();
	}
	
	@Override
	public Optional<ProductDetail> findByProductDetailId(Integer productDetailId) {
		return productDetailRepository.findByProductDetailId(productDetailId);
	}
	
	@Override
	public Optional<ProductDetail> findByProductDetailPK(ProductDetailPK productDetailPK) {
		return productDetailRepository.findByProductDetailPK(productDetailPK);
	}
	
	@Override
	public List<ProductDetail> findByProductCodeUsingRegex(String pattern) {
		return productDetailRepository.findByProductCodeUsingRegex(pattern);
	}
	
	@Override
	public List<ProductDetail> findBySizeCodeUsingRegex(String pattern) {
		return productDetailRepository.findBySizeCodeUsingRegex(pattern);
	}
	
	@Override
	public List<ProductDetail> findByColorCodeUsingRegex(String pattern) {
		return productDetailRepository.findByColorCodeUsingRegex(pattern);
	}
	
	@Override
	public void deleteByProductDetailId(Integer productDetailId) {
		productDetailRepository.deleteByProductDetailId(productDetailId);
	}
	
	@Override
	public void insert(ProductDetailDto productDetailDto) {
	    String sql = "INSERT INTO CHITIETSANPHAM (MASP, MAKICHTHUOC, MAMAUSAC, SOLUONG, HINHANH) VALUES (?, ?, ?, ?, ?)";
	    Query query = entityManager.createNativeQuery(sql);
	    query.setParameter(1, productDetailDto.getProductCode());
	    query.setParameter(2, productDetailDto.getSizeCode());
	    query.setParameter(3, productDetailDto.getColorCode());
	    query.setParameter(4, productDetailDto.getQuantity());
	    query.setParameter(5, productDetailDto.getImage());
	    query.executeUpdate();
	}
	
	@Override
	public void update(ProductDetailDto productDetailDto) {
		 String sql = "UPDATE CHITIETSANPHAM SET HINHANH = ? WHERE IDCTSP = " + productDetailDto.getProductDetailId();
		 Query query = entityManager.createNativeQuery(sql);
		 query.setParameter(1, productDetailDto.getImage());
		 query.executeUpdate();
	}
}

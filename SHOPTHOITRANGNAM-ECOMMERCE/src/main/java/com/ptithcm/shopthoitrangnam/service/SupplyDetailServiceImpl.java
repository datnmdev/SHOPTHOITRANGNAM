package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.SupplyDetailDto;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.Supplier;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;
import com.ptithcm.shopthoitrangnam.repository.SupplyDetailRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplyDetailServiceImpl implements SupplyDetailService {
	@Autowired
	SupplyDetailRepository supplyDetailRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<SupplyDetail> findAll() {
		return supplyDetailRepository.findAll();
	}
	
	@Override
	public Optional<SupplyDetail> findBySupplyDetailId(Integer supplyDetailId) {
		return supplyDetailRepository.findBySupplyDetailId(supplyDetailId);
	}
	
	@Override
	public List<SupplyDetail> findByProductDetail(ProductDetail productDetail) {
		return supplyDetailRepository.findByProductDetail(productDetail);
	}
	
	@Override
	public List<SupplyDetail> findBySupplierAndProductDetail(Supplier supplier, ProductDetail productDetail) {
		return supplyDetailRepository.findBySupplierAndProductDetail(supplier, productDetail);
	}
	
	@Override
	public void deleteBySupplyDetailId(Integer supplyDetailId) {
		supplyDetailRepository.deleteBySupplyDetailId(supplyDetailId);
	}
	
	@Override
	public void deleteBySupplier(Supplier supplier) {
		supplyDetailRepository.deleteBySupplier(supplier);
	}
	
	@Override
	public void insert(SupplyDetailDto supplyDetailDto) {
		String sql = "INSERT INTO CHITIETCUNGCAP(MANCC, IDCTSP) VALUES (?, ?)";
	    Query query = entityManager.createNativeQuery(sql);
	    query.setParameter(1, supplyDetailDto.getSupplierCode());
	    query.setParameter(2, supplyDetailDto.getProductDetailId());
	    query.executeUpdate();
	}
}

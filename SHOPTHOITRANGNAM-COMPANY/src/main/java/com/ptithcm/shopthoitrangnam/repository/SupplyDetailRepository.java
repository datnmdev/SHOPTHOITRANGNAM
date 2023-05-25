package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;

@Repository
public interface SupplyDetailRepository extends JpaRepository<SupplyDetail, Integer>{
	public List<SupplyDetail> findAll();
	
	public Optional<SupplyDetail> findBySupplyDetailId(Integer supplyDetailId);
	
	public List<SupplyDetail> findByProductDetail(ProductDetail productDetail);
	
	public void deleteBySupplyDetailId(Integer supplyDetailId);
	
	public SupplyDetail save(SupplyDetail supplyDetail);
}

package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.SellingPrice;

@Repository
public interface SellingPriceRepository extends JpaRepository<SellingPrice, Long> {
	public List<SellingPrice> findAll();
	
	public Optional<SellingPrice> findBySellingPriceId(Integer sellingPriceId);
	
	public void deleteBySellingPriceId(Integer sellingPriceId);
	
	public SellingPrice save(SellingPrice sellingPrice);
}

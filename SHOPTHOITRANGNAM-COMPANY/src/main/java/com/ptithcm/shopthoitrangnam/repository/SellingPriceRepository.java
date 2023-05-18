package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.SellingPrice;

@Repository
public interface SellingPriceRepository extends JpaRepository<SellingPrice, Long> {
	public void deleteBySellingPriceId(Long sellingPriceId);
}

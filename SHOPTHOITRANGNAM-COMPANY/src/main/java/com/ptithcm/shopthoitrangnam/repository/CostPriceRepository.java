package com.ptithcm.shopthoitrangnam.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.CostPrice;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;

@Repository
public interface CostPriceRepository extends JpaRepository<CostPrice, String> {
	public List<CostPrice> findAll();
	
	public Optional<CostPrice> findByCostPriceId(Integer costPriceId);
	
	public List<CostPrice> findBySupplyDetail(SupplyDetail supplyDetail);
	
	public List<CostPrice> findBySupplyDetailAndEffectiveTime(SupplyDetail supplyDetail, Date effectiveTime);
	
	public void deleteByCostPriceId(Integer costPriceId);
	
	public CostPrice save(CostPrice costPrice);
}

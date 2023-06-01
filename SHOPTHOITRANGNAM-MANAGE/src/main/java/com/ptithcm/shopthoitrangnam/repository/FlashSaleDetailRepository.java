package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.FlashSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;

@Repository
public interface FlashSaleDetailRepository extends JpaRepository<FlashSaleDetail, FlashSaleDetailPK> {
	public List<FlashSaleDetail> findAll();
	
	public Optional<FlashSaleDetail> findByFlashSaleDetailPK(FlashSaleDetailPK flashSaleDetailPK);
	
	public List<FlashSaleDetail> findByFlashSale(FlashSale flashSale);
	
	public void deleteByFlashSaleDetailPK(FlashSaleDetailPK flashSaleDetailPK);
	
	public void deleteAllByFlashSale(FlashSale flashSale);
	
	public FlashSaleDetail save(FlashSaleDetail flashSaleDetail);
}

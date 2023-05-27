package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.FlatRateSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;

@Repository
public interface FlatRateSaleDetailRepository extends JpaRepository<FlatRateSaleDetail, FlatRateSaleDetailPK>{
	public List<FlatRateSaleDetail> findAll();
	
	public Optional<FlatRateSaleDetail> findByFlatRateSaleDetailPK(FlatRateSaleDetailPK flatRateSaleDetailPK);
	
	public List<FlatRateSaleDetail> findByFlatRateSale(FlatRateSale flatRateSale);
	
	public void deleteByFlatRateSaleDetailPK(FlatRateSaleDetailPK flatRateSaleDetailPK);
	
	public void deleteAllByFlatRateSale(FlatRateSale flatRateSale);
	
	public FlatRateSaleDetail save(FlatRateSaleDetail flatRateSaleDetail);
}

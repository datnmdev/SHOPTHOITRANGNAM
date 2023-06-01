package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.SaleOffDetailPK;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;

@Repository
public interface SaleOffDetailRepository extends JpaRepository<SaleOffDetail, SaleOffDetailPK> {
	public List<SaleOffDetail> findAll();
	
	public Optional<SaleOffDetail> findBySaleOffDetailPK(SaleOffDetailPK saleOffDetailPK);
	
	public List<SaleOffDetail> findBySaleOff(SaleOff saleOff);
	
	public void deleteBySaleOffDetailPK(SaleOffDetailPK saleOffDetailPK);
	
	public void deleteAllBySaleOff(SaleOff saleOff);
	
	public SaleOffDetail save(SaleOffDetail saleOffDetail);
}

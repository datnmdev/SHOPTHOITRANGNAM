package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.FlashSale;

@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale, Integer> {
	public List<FlashSale> findAll();
	
	public Optional<FlashSale> findByFlashSaleId(Integer flashSaleId);
	
	@Query(value = "SELECT * FROM FLASHSALE WHERE TENKHUYENMAI LIKE :pattern", nativeQuery = true)
	public List<FlashSale> findByFlashSaleNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByFlashSaleId(Integer flashSaleId);
	
	public FlashSale save(FlashSale flashSale);
}

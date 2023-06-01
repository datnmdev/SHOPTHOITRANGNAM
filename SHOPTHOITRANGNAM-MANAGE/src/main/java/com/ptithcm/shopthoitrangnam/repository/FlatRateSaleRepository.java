package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;

@Repository
public interface FlatRateSaleRepository extends JpaRepository<FlatRateSale, Integer> {
	public List<FlatRateSale> findAll();
	
	public Optional<FlatRateSale> findByFlatRateSaleId(Integer flatRateSaleId);
	
	@Query(value = "SELECT * FROM FLATRATESALE WHERE TENKHUYENMAI LIKE :pattern", nativeQuery = true)
	public List<FlatRateSale> findByFlatRateSaleNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByFlatRateSaleId(Integer flatRateSaleId);
	
	public FlatRateSale save(FlatRateSale flatRateSale);
}

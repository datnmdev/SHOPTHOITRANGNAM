package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.SaleOff;

@Repository
public interface SaleOffRepository extends JpaRepository<SaleOff, Integer> {
	public List<SaleOff> findAll();
	
	public Optional<SaleOff> findBySaleOffId(Integer flashSaleId);
	
	@Query(value = "SELECT * FROM SALEOFF WHERE TENKHUYENMAI LIKE :pattern", nativeQuery = true)
	public List<SaleOff> findBySaleOffNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteBySaleOffId(Integer saleOffId);
	
	public SaleOff save(SaleOff saleOff);
}

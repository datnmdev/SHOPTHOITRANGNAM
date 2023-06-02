package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
	public List<Supplier> findAll();
	
	public Optional<Supplier> findBySupplierCode(String supplierCode); 
	
	@Query(value = "SELECT * FROM NHACUNGCAP WHERE MANCC LIKE :pattern", nativeQuery = true)
	public List<Supplier> findBySupplierCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM NHACUNGCAP WHERE TENNCC LIKE :pattern", nativeQuery = true)
	public List<Supplier> findBySupplierNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteBySupplierCode(String supplierCode);
	
	public Supplier save(Supplier supplier);
}

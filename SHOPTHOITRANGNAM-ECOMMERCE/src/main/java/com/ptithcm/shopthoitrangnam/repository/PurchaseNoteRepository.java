package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;

@Repository
public interface PurchaseNoteRepository extends JpaRepository<PurchaseNote, String> {
	public List<PurchaseNote> findAll();
	
	public Optional<PurchaseNote> findByPurchaseNoteCode(String purchaseNoteCode);
	
	public List<PurchaseNote> findByEmployee(Employee employee);
	
	@Query(value = "SELECT * FROM PHIEUNHAP WHERE MAPHIEUNHAP LIKE :pattern", nativeQuery = true)
	public List<PurchaseNote> findByPurchaseNoteCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM PHIEUNHAP WHERE TENPHIEUNHAP LIKE :pattern", nativeQuery = true)
	public List<PurchaseNote> findByPurchaseNoteNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByPurchaseNoteCode(String purchaseNoteCode);
	
	public PurchaseNote save(PurchaseNote purchaseNote);
}

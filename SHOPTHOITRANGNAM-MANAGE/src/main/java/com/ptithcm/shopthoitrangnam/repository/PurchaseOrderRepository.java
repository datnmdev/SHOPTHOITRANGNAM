package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.Supplier;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {
	public List<PurchaseOrder> findAll();
	
	public Optional<PurchaseOrder> findByPurchaseOrderCode(String purchaseOrderCode);
	
	public List<PurchaseOrder> findBySupplier(Supplier supplier);
	
	@Query(value = "SELECT * FROM PHIEUDAT WHERE MAPHIEUDAT LIKE :pattern", nativeQuery = true)
	public List<PurchaseOrder> findByPurchaseOrderCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM PHIEUDAT WHERE TENPHIEUDAT LIKE :pattern", nativeQuery = true)
	public List<PurchaseOrder> findByPurchaseOrderNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByPurchaseOrderCode(String purchaseOrderCode);
	
	public PurchaseOrder save(PurchaseOrder purchaseOrder);
} 

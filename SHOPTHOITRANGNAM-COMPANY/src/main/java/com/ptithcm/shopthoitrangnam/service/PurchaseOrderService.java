package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.Supplier;

public interface PurchaseOrderService {
	public List<PurchaseOrder> findAll();
	
	public Optional<PurchaseOrder> findByPurchaseOrderCode(String purchaseOrderCode);
	
	public List<PurchaseOrder> findBySupplier(Supplier supplier);
	
	public List<PurchaseOrder> findByPurchaseOrderCodeUsingRegex(String pattern);
	
	public List<PurchaseOrder> findByPurchaseOrderNameUsingRegex(String pattern);
	
	public void deleteByPurchaseOrderCode(String purchaseOrderCode);
	
	public void save(PurchaseOrderDto purchaseOrderDto);
}

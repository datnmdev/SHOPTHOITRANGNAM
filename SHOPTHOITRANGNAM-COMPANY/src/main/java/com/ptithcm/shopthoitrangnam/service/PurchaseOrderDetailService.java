package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDetailDto;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;

public interface PurchaseOrderDetailService {
	public List<PurchaseOrderDetail> findAll();
	
	public Optional<PurchaseOrderDetail> findByPurchaseOrderAndProductDetail(PurchaseOrder purchaseOrder, ProductDetail productDetail);
	
	public List<PurchaseOrderDetail> findByPurchaseOrder(PurchaseOrder purchaseOrder);
	
	public void deleteByPurchaseOrderAndProductDetail(PurchaseOrder purchaseOrder, ProductDetail productDetail);
	
	public void deleteByPurchaseOrder(PurchaseOrder purchaseOrder);
	
	public void update(PurchaseOrderDetailDto purchaseOrderDetailDto);
	
	public void save(PurchaseOrderDetailDto purchaseOrderDetailDto);
}

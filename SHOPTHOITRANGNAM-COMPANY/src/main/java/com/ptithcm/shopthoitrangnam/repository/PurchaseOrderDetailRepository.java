package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.PurchaseOrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;

@Repository
public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, PurchaseOrderDetailPK> {
	public List<PurchaseOrderDetail> findAll();
	
	public Optional<PurchaseOrderDetail> findByPurchaseOrderAndProductDetail(PurchaseOrder purchaseOrder, ProductDetail productDetail);
	
	public List<PurchaseOrderDetail> findByPurchaseOrder(PurchaseOrder purchaseOrder);
	
	public void deleteByPurchaseOrderAndProductDetail(PurchaseOrder purchaseOrder, ProductDetail productDetail);
	
	public PurchaseOrderDetail save(PurchaseOrderDetail purchaseOrderDetail);
}

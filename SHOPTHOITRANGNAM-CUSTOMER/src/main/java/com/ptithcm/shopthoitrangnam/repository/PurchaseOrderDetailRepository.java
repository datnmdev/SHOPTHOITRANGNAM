package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.PurchaseOrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;

@Repository
public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, PurchaseOrderDetailPK> {

}

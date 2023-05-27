package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.PurchaseOrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;
import com.ptithcm.shopthoitrangnam.repository.PurchaseOrderDetailRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchaseOrderDetailServiceImpl implements PurchaseOrderDetailService {
	@Autowired
	PurchaseOrderDetailRepository purchaseOrderDetailRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<PurchaseOrderDetail> findAll() {
		return purchaseOrderDetailRepository.findAll();
	}
	
	@Override
	public List<PurchaseOrderDetail> findByPurchaseOrder(PurchaseOrder purchaseOrder) {
		return purchaseOrderDetailRepository.findByPurchaseOrder(purchaseOrder);
	}
	
	@Override
	public Optional<PurchaseOrderDetail> findByPurchaseOrderAndProductDetail(PurchaseOrder purchaseOrder,
			ProductDetail productDetail) {
		return purchaseOrderDetailRepository.findByPurchaseOrderAndProductDetail(purchaseOrder, productDetail);
	}
	
	@Override
	public void deleteByPurchaseOrderAndProductDetail(PurchaseOrder purchaseOrder, ProductDetail productDetail) {
		purchaseOrderDetailRepository.deleteByPurchaseOrderAndProductDetail(purchaseOrder, productDetail);
	}
	
	@Override
	public void deleteByPurchaseOrder(PurchaseOrder purchaseOrder) {
		purchaseOrderDetailRepository.deleteByPurchaseOrder(purchaseOrder);
	}
	
	@Override
	public void update(PurchaseOrderDetailDto purchaseOrderDetailDto) {
		String sql = "UPDATE CHITIETPHIEUDAT SET SOLUONG = ? WHERE MAPHIEUDAT = ? AND IDCTSP = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, purchaseOrderDetailDto.getQuantity());
		query.setParameter(2, purchaseOrderDetailDto.getPurchaseOrderCode());
		query.setParameter(3, purchaseOrderDetailDto.getProductDetailId());
		query.executeUpdate();
	}
	
	@Override
	public void save(PurchaseOrderDetailDto purchaseOrderDetailDto) {
		PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
		PurchaseOrderDetailPK purchaseOrderDetailPK = new PurchaseOrderDetailPK();
		purchaseOrderDetailPK.setPurchaseOrderCode(purchaseOrderDetailDto.getPurchaseOrderCode());
		purchaseOrderDetailPK.setProductDetailId(purchaseOrderDetailDto.getProductDetailId());
		purchaseOrderDetail.setPurchaseOrderDetailPK(purchaseOrderDetailPK);
		purchaseOrderDetail.setQuantity(purchaseOrderDetailDto.getQuantity());
		purchaseOrderDetailRepository.save(purchaseOrderDetail);
	}
}

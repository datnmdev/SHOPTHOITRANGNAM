package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;
import com.ptithcm.shopthoitrangnam.entity.Supplier;
import com.ptithcm.shopthoitrangnam.repository.PurchaseOrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	SupplierService supplierService;
	
	@Override
	public List<PurchaseOrder> findAll() {
		return purchaseOrderRepository.findAll();
	}
	
	@Override
	public Optional<PurchaseOrder> findByPurchaseOrderCode(String purchaseOrderCode) {
		return purchaseOrderRepository.findByPurchaseOrderCode(purchaseOrderCode);
	}
	
	@Override
	public List<PurchaseOrder> findBySupplier(Supplier supplier) {
		return purchaseOrderRepository.findBySupplier(supplier);
	}
	
	@Override
	public List<PurchaseOrder> findByPurchaseOrderCodeUsingRegex(String pattern) {
		return purchaseOrderRepository.findByPurchaseOrderCodeUsingRegex(pattern);
	}
	
	@Override
	public List<PurchaseOrder> findByPurchaseOrderNameUsingRegex(String pattern) {
		return purchaseOrderRepository.findByPurchaseOrderNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByPurchaseOrderCode(String purchaseOrderCode) {
		purchaseOrderRepository.deleteByPurchaseOrderCode(purchaseOrderCode);
	}
	
	@Override
	public void save(PurchaseOrderDto purchaseOrderDto) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPurchaseOrderCode(purchaseOrderDto.getPurchaseOrderCode());
		purchaseOrder.setPurchaseOrderName(purchaseOrderDto.getPurchaseOrderName());
		purchaseOrder.setDescription(purchaseOrderDto.getDescription());
		purchaseOrder.setCreationTime(new Date());
		purchaseOrder.setSupplier(supplierService.findBySupplierCode(purchaseOrderDto.getSupplierCode()).get());
		if (purchaseOrderDto.getStatus() != null) {
			purchaseOrder.setStatus(purchaseOrderDto.getStatus());
		}
		purchaseOrderRepository.save(purchaseOrder);
	}
}

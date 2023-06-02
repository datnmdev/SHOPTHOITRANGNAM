package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrder;

public class PurchaseOrderMapper {
	public static PurchaseOrderDto toPurchaseOrderDto(PurchaseOrder purchaseOrder) {
		return new PurchaseOrderDto(
				purchaseOrder.getPurchaseOrderCode(), purchaseOrder.getPurchaseOrderName(), 
				purchaseOrder.getDescription(), purchaseOrder.getCreationTime(), 
				purchaseOrder.getSupplier().getSupplierCode(), purchaseOrder.getStatus()
		);
	}
	
	public static List<PurchaseOrderDto> toPurchaseOrderDtos(List<PurchaseOrder> purchaseOrders) {
		return purchaseOrders.stream().map(purchaseOrder -> toPurchaseOrderDto(purchaseOrder)).toList();
	}
}

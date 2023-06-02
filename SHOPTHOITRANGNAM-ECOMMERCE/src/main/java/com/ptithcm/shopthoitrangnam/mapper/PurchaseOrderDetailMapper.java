package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDetailDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;

public class PurchaseOrderDetailMapper {
	public static PurchaseOrderDetailDto toPurchaseOrderDetailDto(PurchaseOrderDetail purchaseOrderDetail) {
		return new PurchaseOrderDetailDto(purchaseOrderDetail.getPurchaseOrderDetailPK().getPurchaseOrderCode(), purchaseOrderDetail.getPurchaseOrderDetailPK().getProductDetailId(), purchaseOrderDetail.getQuantity());
	}
	
	public static List<PurchaseOrderDetailDto> toPurchaseOrderDetailDtos(List<PurchaseOrderDetail> purchaseOrderDetails) {
		return  purchaseOrderDetails.stream().map(purchaseOrderDetail -> toPurchaseOrderDetailDto(purchaseOrderDetail)).toList();
	}
}

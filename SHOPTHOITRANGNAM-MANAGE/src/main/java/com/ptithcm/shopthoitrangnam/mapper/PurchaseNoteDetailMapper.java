package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDetailDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNoteDetail;

public class PurchaseNoteDetailMapper {
	public static PurchaseNoteDetailDto toPurchaseNoteDetailDto(PurchaseNoteDetail purchaseNoteDetail) {
		return new PurchaseNoteDetailDto(purchaseNoteDetail.getPurchaseNoteDetailPK().getPurchaseNoteCode(), purchaseNoteDetail.getPurchaseNoteDetailPK().getProductDetailId(), purchaseNoteDetail.getQuantity());
	}
	
	public static List<PurchaseNoteDetailDto> toPurchaseNoteDetailDtos(List<PurchaseNoteDetail> purchaseNoteDetails) {
		return purchaseNoteDetails.stream().map(purchaseNoteDetail -> toPurchaseNoteDetailDto(purchaseNoteDetail)).toList();
	}
}

package com.ptithcm.shopthoitrangnam.mapper;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDetailDto;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;

public class FlatSaleOffDetailMapper {
	public static FlatRateSaleDetailDto toFlatRateSaleDetailDto(FlatRateSaleDetail flatRateSaleDetail) {
		return new FlatRateSaleDetailDto(flatRateSaleDetail.getFlatRateSaleDetailPK().getFlatRateSaleId(), flatRateSaleDetail.getFlatRateSaleDetailPK().getProductDetailId());
	}
}

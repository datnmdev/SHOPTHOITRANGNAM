package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDetailDto;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;

public class FlatRateSaleDetailMapper {
	public static FlatRateSaleDetailDto toFlatRateSaleDetailDto(FlatRateSaleDetail flatRateSaleDetail) {
		return new FlatRateSaleDetailDto(flatRateSaleDetail.getFlatRateSaleDetailPK().getFlatRateSaleId(), flatRateSaleDetail.getFlatRateSaleDetailPK().getProductDetailId());
	}
	
	public static List<FlatRateSaleDetailDto> toFlatRateSaleDetailDtos(List<FlatRateSaleDetail> flatRateSaleDetails) {
		return flatRateSaleDetails.stream().map(flatRateSaleDetail -> toFlatRateSaleDetailDto(flatRateSaleDetail)).toList();
	}
}

package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDetailDto;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;

public class SaleOffDetailMapper {
	public static SaleOffDetailDto toSaleOffDetailDto(SaleOffDetail saleOffDetail) {
		return new SaleOffDetailDto(saleOffDetail.getSaleOffDetailPK().getSaleOffId(), saleOffDetail.getSaleOffDetailPK().getProductDetailId(), saleOffDetail.getSaleOffPercentage());
	}
	
	public static List<SaleOffDetailDto> toSaleOffDetailDtos(List<SaleOffDetail> saleOffDetails) {
		return saleOffDetails.stream().map(saleOffDetail -> toSaleOffDetailDto(saleOffDetail)).toList();
	}
}	

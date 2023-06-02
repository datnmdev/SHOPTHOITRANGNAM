package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.SupplyDetailDto;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;

public class SupplyDetailMapper {
	public static SupplyDetailDto toSupplyDetailDto(SupplyDetail supplyDetail) {
		return new SupplyDetailDto(supplyDetail.getSupplyDetailPK().getSupplierCode(), supplyDetail.getSupplyDetailPK().getProductDetailId());
	}
	
	public static List<SupplyDetailDto> toSupplyDetailDtos(List<SupplyDetail> supplyDetails) {
		return supplyDetails.stream().map(supplyDetail -> toSupplyDetailDto(supplyDetail)).toList();
	}
}

package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;

public class FlatRateSaleMapper {
	public static FlatRateSaleDto toFlatRateSaleDto(FlatRateSale flatRateSale) {
		return new FlatRateSaleDto(flatRateSale.getFlatRateSaleId(), flatRateSale.getFlatRateSaleName(), flatRateSale.getStartTime(), flatRateSale.getEndTime(), flatRateSale.getPrice());
	}
	
	public static List<FlatRateSaleDto> toFlatRateSaleDtos(List<FlatRateSale> flatRateSales) {
		return flatRateSales.stream().map(flatRateSale -> toFlatRateSaleDto(flatRateSale)).toList();
	}
}

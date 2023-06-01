package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.CostPriceDto;
import com.ptithcm.shopthoitrangnam.entity.CostPrice;

public class CostPriceMapper {
	public static CostPriceDto toCostPriceDto(CostPrice costPrice) {
		return new CostPriceDto(costPrice.getCostPriceId(), costPrice.getPrice(), costPrice.getEffectiveTime(), costPrice.getSupplyDetail().getSupplyDetailId());
	}
	
	public static List<CostPriceDto> toCostPriceDtos(List<CostPrice> costPrices) {
		return costPrices.stream().map(costPrice -> toCostPriceDto(costPrice)).toList();
	}
}

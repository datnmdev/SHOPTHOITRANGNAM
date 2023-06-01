package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.SellingPriceDto;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;

public class SellingPriceMapper {
	public static SellingPriceDto toSellingPriceDto(SellingPrice sellingPrice) {
		return new SellingPriceDto(sellingPrice.getSellingPriceId(), sellingPrice.getPrice(), sellingPrice.getEffectiveTime(), sellingPrice.getProductDetail().getProductDetailId());
	}
	
	public static List<SellingPriceDto> toSellingPriceDtos(List<SellingPrice> sellingPrices) {
		return sellingPrices.stream().map(sellingPrice -> toSellingPriceDto(sellingPrice)).toList();
	}
}

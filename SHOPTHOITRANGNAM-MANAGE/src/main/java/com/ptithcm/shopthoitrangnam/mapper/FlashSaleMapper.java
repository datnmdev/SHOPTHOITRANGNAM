package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;

public class FlashSaleMapper {
	public static FlashSaleDto toFlashSaleDto(FlashSale flashSale) {
		return new FlashSaleDto(flashSale.getFlashSaleId(), flashSale.getFlashSaleName(), flashSale.getStartTime(), flashSale.getEndTime());
	}
	
	public static List<FlashSaleDto> toFlashSaleDtos(List<FlashSale> flashSales) {
		return flashSales.stream().map(flashSale -> toFlashSaleDto(flashSale)).toList();
	}
}

package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDetailDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;

public class FlashSaleDetailMapper {
	public static FlashSaleDetailDto toFlashSaleDetailDto(FlashSaleDetail flashSaleDetail) {
		return new FlashSaleDetailDto(flashSaleDetail.getFlashSaleDetailPK().getFlashSaleId(), flashSaleDetail.getFlashSaleDetailPK().getProductDetailId(), flashSaleDetail.getFlashSalePercentage(), flashSaleDetail.getLimitedQuantity());
	}
	
	public static List<FlashSaleDetailDto> toFlashSaleDetailDtos(List<FlashSaleDetail> flashSaleDetails) {
		return flashSaleDetails.stream().map(flashSaleDetail -> toFlashSaleDetailDto(flashSaleDetail)).toList();
	}
}

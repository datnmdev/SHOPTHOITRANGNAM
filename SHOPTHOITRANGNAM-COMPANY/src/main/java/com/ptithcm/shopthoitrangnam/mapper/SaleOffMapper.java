package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDto;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;

public class SaleOffMapper {
	public static SaleOffDto toOffDto(SaleOff saleOff) {
		return new SaleOffDto(saleOff.getSaleOffId(), saleOff.getSaleOffName(), saleOff.getStartTime(), saleOff.getEndTime());
	}
	
	public static List<SaleOffDto> toSaleOffDtos(List<SaleOff> saleOffs) {
		return saleOffs.stream().map(saleOff -> toOffDto(saleOff)).toList();
	}
}

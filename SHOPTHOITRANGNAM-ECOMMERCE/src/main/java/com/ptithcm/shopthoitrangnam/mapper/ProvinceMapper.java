package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.ProvinceDto;
import com.ptithcm.shopthoitrangnam.entity.Province;

public class ProvinceMapper {
	public static ProvinceDto toProvinceDto(Province province) {
		return new ProvinceDto(province.getProvinceCode(), province.getProvinceName());
	}
	
	public static List<ProvinceDto> toProvinceDtos(List<Province> provinces) {
		return provinces.stream().map(province -> toProvinceDto(province)).toList();
	}
}

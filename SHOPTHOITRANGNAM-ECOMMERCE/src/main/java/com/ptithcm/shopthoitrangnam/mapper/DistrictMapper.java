package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.DistrictDto;
import com.ptithcm.shopthoitrangnam.entity.District;

public class DistrictMapper {
	public static DistrictDto toDistrictDto(District district) {
		return new DistrictDto(district.getDistrictCode(), district.getDistrictName());
	}
	
	public static List<DistrictDto> toDistrictDtos(List<District> districts) {
		return districts.stream().map(district -> toDistrictDto(district)).toList();
	}
}

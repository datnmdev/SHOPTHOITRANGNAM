package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.WardDto;
import com.ptithcm.shopthoitrangnam.entity.Ward;

public class WardMapper {
	public static WardDto toWardDto(Ward ward) {
		return new WardDto(ward.getWardCode(), ward.getWardName());
	}
	
	public static List<WardDto> toWardDtos(List<Ward> wards) {
		return  wards.stream().map(ward -> toWardDto(ward)).toList();
	}
}

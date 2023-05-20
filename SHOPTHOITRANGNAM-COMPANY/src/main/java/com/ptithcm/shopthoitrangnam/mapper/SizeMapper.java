package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.SizeDto;
import com.ptithcm.shopthoitrangnam.entity.Size;

public class SizeMapper {
	public static SizeDto toSizeDto(Size size) {
		return new SizeDto(size.getSizeCode(), size.getSizeName());
	}
	
	public static List<SizeDto> toSizeDtos(List<Size> sizes) {
		return sizes.stream().map(size -> toSizeDto(size)).toList();
	}
}

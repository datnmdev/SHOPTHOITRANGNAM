package com.ptithcm.shopthoitrangnam.mapper;

import com.ptithcm.shopthoitrangnam.dto.SizeDto;
import com.ptithcm.shopthoitrangnam.entity.Size;

public class SizeMapper {
	public static SizeDto toSizeDto(Size size) {
		return new SizeDto(size.getSizeCode(), size.getSizeName());
	}
}

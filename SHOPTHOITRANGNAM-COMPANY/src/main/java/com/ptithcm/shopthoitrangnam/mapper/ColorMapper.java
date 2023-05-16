package com.ptithcm.shopthoitrangnam.mapper;

import com.ptithcm.shopthoitrangnam.dto.ColorDto;
import com.ptithcm.shopthoitrangnam.entity.Color;

public class ColorMapper {
	public static ColorDto toColorDto(Color color) {
		return new ColorDto(color.getColorCode(), color.getColorName());
	}
}

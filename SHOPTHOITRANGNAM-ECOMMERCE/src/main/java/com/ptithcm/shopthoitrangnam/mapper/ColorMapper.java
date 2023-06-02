package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.ColorDto;
import com.ptithcm.shopthoitrangnam.entity.Color;

public class ColorMapper {
	public static ColorDto toColorDto(Color color) {
		return new ColorDto(color.getColorCode(), color.getColorName());
	}
	
	public static List<ColorDto> toColorDtos(List<Color> colors) {
		return colors.stream().map(color -> toColorDto(color)).toList();
	}
}

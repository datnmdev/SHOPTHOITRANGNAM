package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.PositionDto;
import com.ptithcm.shopthoitrangnam.entity.Position;

public class PositionMapper {
	public static PositionDto toPositionDto(Position position) {
		return new PositionDto(position.getPositionCode(), position.getPositionName(), position.getDescription());
	}
	
	public static List<PositionDto> toPositionDtos(List<Position> positions) {
		return positions.stream().map(position -> toPositionDto(position)).toList();
	}
}

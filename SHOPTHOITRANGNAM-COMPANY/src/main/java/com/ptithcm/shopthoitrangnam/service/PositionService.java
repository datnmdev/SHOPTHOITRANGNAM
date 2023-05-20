package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.PositionDto;
import com.ptithcm.shopthoitrangnam.entity.Position;

public interface PositionService {
public List<Position> findAll();
	
	public Optional<Position> findByPositionCode(String positionCode);
	
	public List<Position> findByPositionCodeUsingRegex(String pattern);
	
	public List<Position> findByPositionNameUsingRegex(String pattern);
	
	public void deleteByPositionCode(String positionCode);
	
	public void save(PositionDto positionDto);
}

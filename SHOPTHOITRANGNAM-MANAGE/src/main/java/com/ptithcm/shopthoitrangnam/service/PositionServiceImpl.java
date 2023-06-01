package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.PositionDto;
import com.ptithcm.shopthoitrangnam.entity.Position;
import com.ptithcm.shopthoitrangnam.repository.PositionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {
	@Autowired
	PositionRepository positionRepository;
	
	@Override
	public List<Position> findAll() {
		return positionRepository.findAll();
	}
	
	@Override
	public Optional<Position> findByPositionCode(String positionCode) {
		return positionRepository.findByPositionCode(positionCode);
	}
	
	@Override
	public List<Position> findByPositionCodeUsingRegex(String pattern) {
		return positionRepository.findByPositionCodeUsingRegex(pattern);
	}
	
	@Override
	public List<Position> findByPositionNameUsingRegex(String pattern) {
		return positionRepository.findByPositionNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByPositionCode(String positionCode) {
		positionRepository.deleteByPositionCode(positionCode);
	}
	
	@Override
	public void save(PositionDto positionDto) {
		Position position = new Position();
		position.setPositionCode(positionDto.getPositionCode());
		position.setPositionName(positionDto.getPositionName());
		position.setDescription(positionDto.getDescription());
		positionRepository.save(position);
	}
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.ColorDto;
import com.ptithcm.shopthoitrangnam.entity.Color;
import com.ptithcm.shopthoitrangnam.repository.ColorRepository;

import jakarta.transaction.Transactional;

@Service
public class ColorServiceImpl implements ColorService {
	@Autowired
	ColorRepository colorRepository;
	
	@Override
	public List<Color> findAll() {
		return colorRepository.findAll();
	}
	
	@Override
	public Optional<Color> findByColorCode(String colorCode) {
		return colorRepository.findByColorCode(colorCode);
	}
	
	@Override
	public List<Color> findByColorCodeUsingRegex(String pattern) {
		return colorRepository.findByColorCodeUsingRegex(pattern);
	}
	
	@Override
	public List<Color> findByColorNameUsingRegex(String pattern) {
		return colorRepository.findByColorNameUsingRegex(pattern);
	}
	
	@Override
	@Transactional
	public void deleteByColorCode(String colorCode) {
		colorRepository.deleteByColorCode(colorCode);
	}
	
	@Override
	@Transactional
	public void save(ColorDto colorDto) {
		Color color = new Color();
		color.setColorCode(colorDto.getColorCode());
		color.setColorName(colorDto.getColorName());
		colorRepository.save(color);
	}
}

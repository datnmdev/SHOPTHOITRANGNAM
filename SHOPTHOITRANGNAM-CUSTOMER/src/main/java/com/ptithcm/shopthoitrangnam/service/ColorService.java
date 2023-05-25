package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.ColorDto;
import com.ptithcm.shopthoitrangnam.entity.Color;

public interface ColorService {
	public List<Color> findAll();
	
	public Optional<Color> findByColorCode(String colorCode);
	
	public List<Color> findByColorCodeUsingRegex(String pattern);
	
	public List<Color> findByColorNameUsingRegex(String pattern);
	
	public void deleteByColorCode(String colorCode);
	
	public void save(ColorDto colorDto);
}

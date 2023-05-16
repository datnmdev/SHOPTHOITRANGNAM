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
	
	@Query(value = "SELECT * FROM MAUSAC WHERE MAMAUSAC LIKE :pattern", nativeQuery = true)
	public List<Color> findByColorCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM MAUSAC WHERE TENMAUSAC LIKE :pattern", nativeQuery = true)
	public List<Color> findByColorNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByColorCode(String colorCode);
	
	public void save(ColorDto colorDto);
}

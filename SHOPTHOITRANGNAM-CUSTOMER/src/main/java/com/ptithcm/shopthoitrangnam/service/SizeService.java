package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.SizeDto;
import com.ptithcm.shopthoitrangnam.entity.Size;

public interface SizeService {
	public List<Size> findAll();
	
	public Optional<Size> findBySizeCode(String sizeCode);
	
	public List<Size> findBySizeCodeUsingRegex(String pattern);
	
	public List<Size> findBySizeNameUsingRegex(String pattern);
	
	public void deleteBySizeCode(String sizeCode);
	
	public void save(SizeDto sizeDto);
}

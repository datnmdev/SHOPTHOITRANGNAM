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
	
	@Query(value = "SELECT * FROM KICHTHUOC WHERE MAKICHTHUOC LIKE :pattern", nativeQuery = true)
	public List<Size> findBySizeCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM KICHTHUOC WHERE TENKICHTHUOC LIKE :pattern", nativeQuery = true)
	public List<Size> findBySizeNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteBySizeCode(String sizeCode);
	
	public void save(SizeDto sizeDto);
}

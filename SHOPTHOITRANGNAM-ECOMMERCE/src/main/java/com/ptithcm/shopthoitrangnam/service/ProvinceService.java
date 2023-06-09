package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.Province;

public interface ProvinceService {
	public List<Province> findAll();
	
	public Optional<Province> findByProvinceCode(String provinceCode);
}

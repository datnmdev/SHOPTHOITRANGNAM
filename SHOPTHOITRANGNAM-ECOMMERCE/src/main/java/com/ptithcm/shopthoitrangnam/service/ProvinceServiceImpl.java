package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.Province;
import com.ptithcm.shopthoitrangnam.repository.ProvinceRepository;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	ProvinceRepository provinceRepository;
	
	@Override
	public List<Province> findAll() {
		return provinceRepository.findAll();
	}
	
	@Override
	public Optional<Province> findByProvinceCode(String provinceCode) {
		return provinceRepository.findByProvinceCode(provinceCode);
	}
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.District;
import com.ptithcm.shopthoitrangnam.entity.Province;
import com.ptithcm.shopthoitrangnam.repository.DistrictRepository;

@Service
public class DistrictServiceImpl implements DistrictService {
	@Autowired
	DistrictRepository districtRepository;
	
	@Override
	public List<District> findByProvince(Province province) {
		return districtRepository.findByProvince(province);
	}
	
	@Override
	public Optional<District> findByDistrictCode(String districtCode) {
		return districtRepository.findByDistrictCode(districtCode);
	}
}

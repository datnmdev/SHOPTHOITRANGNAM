package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.District;
import com.ptithcm.shopthoitrangnam.entity.Province;

public interface DistrictService {
	public List<District> findByProvince(Province province);
	
	public Optional<District> findByDistrictCode(String districtCode);
}

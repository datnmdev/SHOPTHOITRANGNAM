package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.District;
import com.ptithcm.shopthoitrangnam.entity.Ward;

public interface WardService {
	public List<Ward> findByDistrict(District district);
	
	public Optional<Ward> findByWardCode(String wardCode);
}

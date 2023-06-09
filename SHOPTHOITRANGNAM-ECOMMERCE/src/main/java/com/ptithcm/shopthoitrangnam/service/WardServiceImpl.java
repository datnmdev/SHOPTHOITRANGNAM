package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.District;
import com.ptithcm.shopthoitrangnam.entity.Ward;
import com.ptithcm.shopthoitrangnam.repository.WardRepository;

@Service
public class WardServiceImpl implements WardService {
	@Autowired
	WardRepository wardRepository;
	
	@Override
	public List<Ward> findByDistrict(District district) {
		return wardRepository.findByDistrict(district);
	}
	
	@Override
	public Optional<Ward> findByWardCode(String wardCode) {
		return wardRepository.findByWardCode(wardCode);
	}
}

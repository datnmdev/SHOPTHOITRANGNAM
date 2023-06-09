package com.ptithcm.shopthoitrangnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ptithcm.shopthoitrangnam.dto.DistrictDto;
import com.ptithcm.shopthoitrangnam.dto.ProvinceDto;
import com.ptithcm.shopthoitrangnam.dto.WardDto;
import com.ptithcm.shopthoitrangnam.mapper.DistrictMapper;
import com.ptithcm.shopthoitrangnam.mapper.ProvinceMapper;
import com.ptithcm.shopthoitrangnam.mapper.WardMapper;
import com.ptithcm.shopthoitrangnam.service.DistrictService;
import com.ptithcm.shopthoitrangnam.service.ProvinceService;
import com.ptithcm.shopthoitrangnam.service.WardService;

@Controller
public class AddressController {
	@Autowired
	ProvinceService provinceService;
	
	@Autowired
	DistrictService districtService;
	
	@Autowired
	WardService wardService;
	
	@GetMapping("/provinces")
	@ResponseBody
	public List<ProvinceDto> getProvinceDtos() {
		return ProvinceMapper.toProvinceDtos(provinceService.findAll());
	}
	
	@GetMapping(value = "/districts", params = "provinceCode")
	@ResponseBody
	public List<DistrictDto> getDistrictDtos(@RequestParam("provinceCode") String provinceCode) {
		return DistrictMapper.toDistrictDtos(districtService.findByProvince(provinceService.findByProvinceCode(provinceCode).get()));
	}
	
	@GetMapping(value = "/wards", params = "districtCode")
	@ResponseBody
	public List<WardDto> getWardDtos(@RequestParam("districtCode") String districtCode) {
		return WardMapper.toWardDtos(wardService.findByDistrict(districtService.findByDistrictCode(districtCode).get()));
	}
}

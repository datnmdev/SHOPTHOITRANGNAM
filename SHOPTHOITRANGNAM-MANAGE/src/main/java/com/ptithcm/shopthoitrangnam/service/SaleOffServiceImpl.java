package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDto;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.repository.SaleOffRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SaleOffServiceImpl implements SaleOffService {
	@Autowired
	SaleOffRepository saleOffRepository;
	
	@Override
	public List<SaleOff> findAll() {
		return saleOffRepository.findAll();
	}
	
	@Override
	public Optional<SaleOff> findBySaleOffId(Integer flashSaleId) {
		return saleOffRepository.findBySaleOffId(flashSaleId);
	}
	
	@Override
	public List<SaleOff> findBySaleOffNameUsingRegex(String pattern) {
		return saleOffRepository.findBySaleOffNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteBySaleOffId(Integer saleOffId) {
		saleOffRepository.deleteBySaleOffId(saleOffId);
	}
	
	@Override
	public void save(SaleOffDto saleOffDto) {
		SaleOff saleOff = new SaleOff();
		saleOff.setSaleOffId(saleOffDto.getSaleOffId());
		saleOff.setSaleOffName(saleOffDto.getSaleOffName());
		saleOff.setStartTime(saleOffDto.getStartTime());
		saleOff.setEndTime(saleOffDto.getEndTime());
		
		saleOffRepository.save(saleOff);
	}
}

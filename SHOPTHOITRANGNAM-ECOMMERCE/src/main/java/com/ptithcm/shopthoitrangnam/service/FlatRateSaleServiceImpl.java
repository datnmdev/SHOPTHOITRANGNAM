package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.repository.FlatRateSaleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlatRateSaleServiceImpl implements FlatRateSaleService {
	@Autowired
	FlatRateSaleRepository flatRateSaleRepository;
	
	@Override
	public List<FlatRateSale> findAll() {
		return flatRateSaleRepository.findAll();
	}
	
	@Override
	public Optional<FlatRateSale> findByFlatRateSaleId(Integer flatRateSaleId) {
		return flatRateSaleRepository.findByFlatRateSaleId(flatRateSaleId);
	}
	
	@Override
	public List<FlatRateSale> findByFlatRateSaleNameUsingRegex(String pattern) {
		return flatRateSaleRepository.findByFlatRateSaleNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByFlatRateSaleId(Integer flatRateSaleId) {
		flatRateSaleRepository.deleteByFlatRateSaleId(flatRateSaleId);
	}
	
	@Override
	public void save(FlatRateSaleDto flatRateSaleDto) {
		FlatRateSale flatRateSale = new FlatRateSale();
		flatRateSale.setFlatRateSaleId(flatRateSaleDto.getFlatRateSaleId());
		flatRateSale.setFlatRateSaleName(flatRateSaleDto.getFlatRateSaleName());
		flatRateSale.setStartTime(flatRateSaleDto.getStartTime());
		flatRateSale.setEndTime(flatRateSaleDto.getEndTime());
		flatRateSale.setPrice(flatRateSaleDto.getPrice());
		flatRateSaleRepository.save(flatRateSale);
	}
}

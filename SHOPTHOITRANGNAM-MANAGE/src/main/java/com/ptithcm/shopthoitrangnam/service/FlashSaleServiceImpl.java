package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.repository.FlashSaleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlashSaleServiceImpl implements FlashSaleService {
	@Autowired
	FlashSaleRepository flashSaleRepository;
	
	@Override
	public List<FlashSale> findAll() {
		return flashSaleRepository.findAll();
	}
	
	@Override
	public Optional<FlashSale> findByFlashSaleId(Integer flashSaleId) {
		return flashSaleRepository.findByFlashSaleId(flashSaleId);
	}
	
	@Override
	public List<FlashSale> findByFlashSaleNameUsingRegex(String pattern) {
		return flashSaleRepository.findByFlashSaleNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByFlashSaleId(Integer flashSaleId) {
		flashSaleRepository.deleteByFlashSaleId(flashSaleId);
	}
	
	@Override
	public void save(FlashSaleDto flashSaleDto) {
		FlashSale flashSale = new FlashSale();
		flashSale.setFlashSaleId(flashSaleDto.getFlashSaleId());
		flashSale.setFlashSaleName(flashSaleDto.getFlashSaleName());
		flashSale.setStartTime(flashSaleDto.getStartTime());
		flashSale.setEndTime(flashSaleDto.getEndTime());
		
		flashSaleRepository.save(flashSale);
	}
}

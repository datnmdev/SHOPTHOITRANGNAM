package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.SellingPriceDto;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.repository.SellingPriceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SellingPriceServiceImpl implements SellingPriceService {
	@Autowired
	SellingPriceRepository sellingPriceRepository;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Override
	public List<SellingPrice> findAll() {
		return sellingPriceRepository.findAll();
	}
	
	@Override
	public Optional<SellingPrice> findBySellingPriceId(Integer sellingPriceId) {
		return sellingPriceRepository.findBySellingPriceId(sellingPriceId);
	}
	
	@Override
	public void deleteBySellingPriceId(Integer sellingPriceId) {
		sellingPriceRepository.deleteBySellingPriceId(sellingPriceId);
	}
	
	@Override
	public void deleteAll(List<SellingPrice> sellingPrices) {
		for (SellingPrice sellingPrice : sellingPrices) {
			deleteBySellingPriceId(sellingPrice.getSellingPriceId());
		}
	}
	
	@Override
	public void save(SellingPriceDto sellingPriceDto) {
		SellingPrice sellingPrice = new SellingPrice();
		sellingPrice.setSellingPriceId(sellingPriceDto.getSellingPriceId());
		sellingPrice.setPrice(sellingPriceDto.getPrice());
		sellingPrice.setEffectiveTime(sellingPriceDto.getEffectiveTime());
		sellingPrice.setProductDetail(productDetailService.findByProductDetailId(sellingPriceDto.getProductDetailId()).get());
		sellingPriceRepository.save(sellingPrice);
	}
}

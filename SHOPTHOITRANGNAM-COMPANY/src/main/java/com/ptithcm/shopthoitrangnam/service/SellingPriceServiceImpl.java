package com.ptithcm.shopthoitrangnam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.repository.SellingPriceRepository;

import jakarta.transaction.Transactional;

@Service
public class SellingPriceServiceImpl implements SellingPriceService {
	@Autowired
	SellingPriceRepository sellingPriceRepository;
	
	@Override
	@Transactional
	public void deleteBySellingPriceId(Long sellingPriceId) {
		sellingPriceRepository.deleteBySellingPriceId(sellingPriceId);
	}
	
	@Override
	@Transactional
	public void deleteAll(List<SellingPrice> sellingPrices) {
		for (SellingPrice sellingPrice : sellingPrices) {
			deleteBySellingPriceId(sellingPrice.getSellingPriceId());
		}
	}
}

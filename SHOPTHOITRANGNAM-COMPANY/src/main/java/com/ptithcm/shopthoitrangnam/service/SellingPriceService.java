package com.ptithcm.shopthoitrangnam.service;

import java.util.List;

import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;

public interface SellingPriceService {
	public void deleteBySellingPriceId(Long sellingPriceId);
	
	public void deleteAll(List<SellingPrice> sellingPrices);
}

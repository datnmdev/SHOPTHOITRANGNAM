package com.ptithcm.shopthoitrangnam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductRating;
import com.ptithcm.shopthoitrangnam.repository.ProductRatingRepository;

@Service
public class ProductRatingServiceImpl implements ProductRatingService {
	@Autowired
	ProductRatingRepository productRatingRepository;
	
}

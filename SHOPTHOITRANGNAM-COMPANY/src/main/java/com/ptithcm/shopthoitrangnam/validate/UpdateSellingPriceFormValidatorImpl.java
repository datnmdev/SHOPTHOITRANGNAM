package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.SellingPriceDto;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.SellingPriceService;

@Component("updateSellingPriceFormValidator")
public class UpdateSellingPriceFormValidatorImpl implements Validator {
	@Autowired
	SellingPriceService sellingPriceService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SellingPriceDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		SellingPriceDto sellingPriceDto = (SellingPriceDto) target;
		ProductDetail productDetail = productDetailService.findByProductDetailId(sellingPriceDto.getProductDetailId()).get();
		if (!sellingPriceService.findByProductDetailAndEffectiveTime(productDetail, sellingPriceDto.getEffectiveTime()).isEmpty()
				&& sellingPriceService.findBySellingPriceId(sellingPriceDto.getSellingPriceId()).get().getEffectiveTime().getTime() != sellingPriceDto.getEffectiveTime().getTime()) {
			errors.rejectValue("effectiveTime", "effectiveTime", "(*) Mốc thời gian áp dụng giá đã tồn tại");
		}
	}
}

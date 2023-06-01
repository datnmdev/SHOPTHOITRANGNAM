package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDetailDto;

@Component("updateFlashSaleDetailFormValidator")
public class UpdateFlashSaleDetailFormValidatorImpl implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return FlashSaleDetailDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
	}
}

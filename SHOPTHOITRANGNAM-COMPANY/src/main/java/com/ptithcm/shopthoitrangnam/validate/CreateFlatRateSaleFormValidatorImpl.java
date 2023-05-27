package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDto;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;

@Controller("createFlatRateSaleFormValidator")
public class CreateFlatRateSaleFormValidatorImpl implements Validator {
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FlatRateSaleDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		FlatRateSaleDto flaRateSaleDto = (FlatRateSaleDto) target;
		if (flaRateSaleDto.getEndTime() != null && flaRateSaleDto.getStartTime() != null  && flaRateSaleDto.getEndTime().getTime() <= flaRateSaleDto.getStartTime().getTime()) {
			errors.rejectValue("endTime", "endTime", "(*) Thời điểm kết thúc phải lớn hơn thời điểm bắt đầu");
		}
	}
}

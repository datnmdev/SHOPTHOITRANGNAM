package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.SizeDto;
import com.ptithcm.shopthoitrangnam.service.SizeService;

@Component("createSizeFormValidator")
public class CreateSizeFormValidatorImpl implements Validator {
	@Autowired
	SizeService sizeService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SizeDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		SizeDto sizeDto = (SizeDto) target;
		if (sizeService.findBySizeCode(sizeDto.getSizeCode()).isPresent()) {
			errors.rejectValue("sizeCode", "sizeCode", "(*) Mã kích thước này đã tồn tại");
		}
	}
}

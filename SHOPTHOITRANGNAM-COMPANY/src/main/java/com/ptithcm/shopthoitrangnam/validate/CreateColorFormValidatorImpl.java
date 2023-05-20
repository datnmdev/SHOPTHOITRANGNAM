package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.ColorDto;
import com.ptithcm.shopthoitrangnam.service.ColorService;

@Component("createColorFormValidator")
public class CreateColorFormValidatorImpl implements Validator {
	@Autowired
	ColorService colorService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ColorDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ColorDto colorDto = (ColorDto) target;
		if (colorService.findByColorCode(colorDto.getColorCode()).isPresent()) {
			errors.rejectValue("colorCode", "colorCode", "(*) Mã màu sắc này đã tồn tại");
		}
	}
}

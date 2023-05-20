package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.PositionDto;
import com.ptithcm.shopthoitrangnam.service.PositionService;

@Component("createPositionFormValidator")
public class CreatePositionFormValidatorImp implements Validator {
	@Autowired
	PositionService positionService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PositionDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		PositionDto positionDto = (PositionDto) target;
		if (positionService.findByPositionCode(positionDto.getPositionCode()).isPresent()) {
			errors.rejectValue("positionCode", "positionCode", "(*) Mã chức vụ này đã tồn tại");
		}
	}
}

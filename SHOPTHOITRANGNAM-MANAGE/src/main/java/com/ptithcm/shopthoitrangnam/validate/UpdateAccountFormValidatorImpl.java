package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.AccountDto;

@Component("updateAccountFormValidator")
public class UpdateAccountFormValidatorImpl implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		AccountDto accountDto = (AccountDto) target;
		if (!accountDto.getPassword().isEmpty() && accountDto.getSalt().isEmpty()) {
			errors.rejectValue("salt", "salt", "(*) Trường này không được bỏ trống");
		}
	}
}

package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.AccountDto;

@Component("createAccountFormValidator")
public class CreateAccountFormValidatorImpl implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		AccountDto accountDto = (AccountDto) target;
		if (accountDto.getPassword().isEmpty()) {
			errors.rejectValue("password", "password", "(*) Trường này không được bỏ trống");
		}
		
		if (accountDto.getSalt().isEmpty()) {
			errors.rejectValue("salt", "salt", "(*) Trường này không được bỏ trống");
		}
	}
}

package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.UserDto;

@Component("registerFormValidator")
public class RegisterFormValidatorImpl implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		UserDto userDto = (UserDto) target;
		
		if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "confirmPassword", "(*) Mật khẩu không khớp");
		}
	}
}

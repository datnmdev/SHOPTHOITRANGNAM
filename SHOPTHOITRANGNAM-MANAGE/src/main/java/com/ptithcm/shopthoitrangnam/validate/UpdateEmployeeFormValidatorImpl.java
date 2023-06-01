package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.EmployeeDto;

@Component("updateEmployeeFormValidator")
public class UpdateEmployeeFormValidatorImpl implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		EmployeeDto employeeDto = (EmployeeDto) target;
		
		String fileExtention = employeeDto.getImage().substring(employeeDto.getImage().lastIndexOf(".") + 1);
		if (!fileExtention.equals("jpeg") && !fileExtention.equals("jpg") && 
				!fileExtention.equals("png") && !fileExtention.equals("webp") && !fileExtention.equals("jfif")) {
			errors.rejectValue("image", "image" ,"(*) File tải lên phải có định dạng .png, .jpg, .jpeg, .jfif hoặc webp");
		}
	}
}

package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.SupplierDto;
import com.ptithcm.shopthoitrangnam.service.SupplierService;

@Component("createSupplierFormValidator")
class CreateSupplierFormValidatorImpl implements Validator {
	@Autowired
	SupplierService supplierService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SupplierDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		SupplierDto supplierDto = (SupplierDto) target;
		if (supplierService.findBySupplierCode(supplierDto.getSupplierCode()).isPresent()) {
			errors.rejectValue("supplierCode", "supplierCode", "(*) Mã nhà cung cấp này đã tồn tại");
		}
	}
}

package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.PurchaseOrderDto;
import com.ptithcm.shopthoitrangnam.service.PurchaseOrderService;

@Controller("createPurchaseOrderFormValidator")
public class CreatePurchaseOrderFormValidatorImpl implements Validator {
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseOrderDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		PurchaseOrderDto purchaseOrderDto = (PurchaseOrderDto) target;
		if (purchaseOrderService.findByPurchaseOrderCode(purchaseOrderDto.getPurchaseOrderCode()).isPresent()) {
			errors.rejectValue("purchaseOrderCode", "purchaseOrderCode", "(*) Mã phiếu đặt này đã tồn tại");
		}
	}
}

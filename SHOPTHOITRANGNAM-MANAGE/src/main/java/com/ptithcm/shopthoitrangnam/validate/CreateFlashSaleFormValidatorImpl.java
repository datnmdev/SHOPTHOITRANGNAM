package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;

@Component("createFlashSaleFormValidator")
public class CreateFlashSaleFormValidatorImpl implements Validator {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FlashSaleDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		FlashSaleDto flashSaleDto = (FlashSaleDto) target;
		List<FlashSale> flashSales = flashSaleService.findAll();
		if (flashSaleDto.getStartTime() != null && flashSales.stream().anyMatch(flashSale -> flashSaleDto.getStartTime().getTime() >= flashSale.getStartTime().getTime() && flashSaleDto.getStartTime().getTime() <= flashSale.getEndTime().getTime())) {
			errors.rejectValue("startTime", "startTime", "(*) Mốc thời gian này hiện là thởi điểm đang diễn ra flash sale khác");
		}
		
		if (flashSaleDto.getEndTime() != null && flashSaleDto.getEndTime().getTime() <= flashSaleDto.getStartTime().getTime()) {
			errors.rejectValue("endTime", "endTime", "(*) Thời điểm kết thúc phải lớn hơn thời điểm bắt đầu");
		}
		
		if (flashSaleDto.getEndTime() != null && flashSales.stream().anyMatch(flashSale -> flashSaleDto.getEndTime().getTime() >= flashSale.getStartTime().getTime() && flashSaleDto.getEndTime().getTime() <= flashSale.getEndTime().getTime())) {
			errors.rejectValue("endTime", "endTime", "(*) Mốc thời gian này hiện là thởi điểm đang diễn ra flash sale khác");
		}
	}
}

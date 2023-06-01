package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDto;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Component("createSaleOffFormValidator")
public class CreateSaleOffFormValidatorImpl implements Validator {
	@Autowired
	SaleOffService saleOffService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SaleOffDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		SaleOffDto saleOffDto = (SaleOffDto) target;
		List<SaleOff> saleOffs = saleOffService.findAll();
		if (saleOffDto.getStartTime() != null && saleOffDto.getStartTime() != null && saleOffs.stream().anyMatch(saleOff -> saleOffDto.getStartTime().getTime() >= saleOff.getStartTime().getTime() && saleOffDto.getStartTime().getTime() <= saleOff.getEndTime().getTime())) {
			errors.rejectValue("startTime", "startTime", "(*) Mốc thời gian này hiện là thởi điểm đang diễn ra sale off khác");
		}
		
		if (saleOffDto.getEndTime() != null && saleOffDto.getEndTime().getTime() <= saleOffDto.getStartTime().getTime()) {
			errors.rejectValue("endTime", "endTime", "(*) Thời điểm kết thúc phải lớn hơn thời điểm bắt đầu");
		}
		
		if (saleOffDto.getEndTime() != null && saleOffs.stream().anyMatch(saleOff -> saleOffDto.getEndTime().getTime() >= saleOff.getStartTime().getTime() && saleOffDto.getEndTime().getTime() <= saleOff.getEndTime().getTime())) {
			errors.rejectValue("endTime", "endTime", "(*) Mốc thời gian này hiện là thởi điểm đang diễn ra sale off khác");
		}
	}
}

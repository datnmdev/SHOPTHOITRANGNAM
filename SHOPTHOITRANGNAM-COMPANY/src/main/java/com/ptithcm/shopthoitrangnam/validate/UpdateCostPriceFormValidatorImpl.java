package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.CostPriceDto;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;
import com.ptithcm.shopthoitrangnam.service.CostPriceService;
import com.ptithcm.shopthoitrangnam.service.SupplyDetailService;

@Component("updateCostPriceFormValidator")
public class UpdateCostPriceFormValidatorImpl implements Validator {
	@Autowired
	CostPriceService costPriceService;
	
	@Autowired
	SupplyDetailService supplyDetailService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CostPriceDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		CostPriceDto costPriceDto = (CostPriceDto) target;
		SupplyDetail supplyDetail = supplyDetailService.findBySupplyDetailId(costPriceDto.getSupplyDetailId()).get();
		if (!costPriceService.findBySupplyDetailAndEffectiveTime(supplyDetail, costPriceDto.getEffectiveTime()).isEmpty()
				&& costPriceService.findByCostPriceId(costPriceDto.getCostPriceId()).get().getEffectiveTime().getTime() != costPriceDto.getEffectiveTime().getTime()) {
			errors.rejectValue("effectiveTime", "effectiveTime", "(*) Mốc thời gian áp dụng giá đã tồn tại");
		}
	}
}

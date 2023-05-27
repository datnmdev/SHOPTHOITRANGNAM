package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Component("updateFlatRateSaleFormValidator")
public class UpdateFlatRateSaleFormValidatorImpl implements Validator {
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FlatRateSaleDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		FlatRateSaleDto flatRateSaleDto = (FlatRateSaleDto) target;
		if (flatRateSaleDto.getEndTime().getTime() <= flatRateSaleDto.getStartTime().getTime()) {
			errors.rejectValue("endTime", "endTime", "(*) Thời điểm kết thúc phải lớn hơn thời điểm bắt đầu");
		}
		
		FlatRateSale flatRateSale = flatRateSaleService.findByFlatRateSaleId(flatRateSaleDto.getFlatRateSaleId()).get();
		List<FlashSale> flashSales = flashSaleService.findAll().stream().
				filter(flashSale -> (flatRateSale.getStartTime().getTime() >= flashSale.getStartTime().getTime() && flatRateSale.getStartTime().getTime() <= flashSale.getEndTime().getTime()) || (flatRateSale.getEndTime().getTime() >= flashSale.getStartTime().getTime() && flatRateSale.getEndTime().getTime() <= flashSale.getEndTime().getTime())).toList();
		List<SaleOff> saleOffs = saleOffService.findAll().stream().
				filter(saleOff -> (flatRateSale.getStartTime().getTime() >= saleOff.getStartTime().getTime() && flatRateSale.getStartTime().getTime() <= saleOff.getEndTime().getTime()) || (flatRateSale.getEndTime().getTime() >= saleOff.getStartTime().getTime() && flatRateSale.getEndTime().getTime() <= saleOff.getEndTime().getTime())).toList();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().
				filter(flatRateSale_ -> (flatRateSale.getStartTime().getTime() >= flatRateSale_.getStartTime().getTime() && flatRateSale.getStartTime().getTime() <= flatRateSale_.getEndTime().getTime()) || (flatRateSale.getEndTime().getTime() >= flatRateSale_.getStartTime().getTime() && flatRateSale.getEndTime().getTime() <= flatRateSale_.getEndTime().getTime())).toList();
		flatRateSales.remove(flatRateSaleService.findByFlatRateSaleId(flatRateSaleDto.getFlatRateSaleId()).get());
		if (flashSales.stream().anyMatch(flashSale -> flashSale.getFlashSaleDetails().stream().anyMatch(flashSaleDetail -> flatRateSale.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail().getProductDetailId() == flashSaleDetail.getProductDetail().getProductDetailId())))
				|| saleOffs.stream().anyMatch(saleOff -> saleOff.getSaleOffDetails().stream().anyMatch(saleOffDetail -> flatRateSale.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail().getProductDetailId().equals(saleOffDetail.getProductDetail().getProductDetailId()))))
				|| flatRateSales.stream().anyMatch(flatRateSale_ -> flatRateSale_.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail_ -> flatRateSale.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail().getProductDetailId().equals(flatRateSaleDetail_.getProductDetail().getProductDetailId()))))) {
			errors.rejectValue("startTime", "startTime", "(*) Mốc thời gian này đang được áp dụng trong một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
			errors.rejectValue("endTime", "endTime", "(*) Mốc thời gian này đang được áp dụng trong một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
		}
	}
}

package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Component("updateFlashSaleFormValidator")
public class UpdateFlashSaleFormValidatorImpl implements Validator {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
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
		
		if (flashSaleDto.getEndTime().getTime() <= flashSaleDto.getStartTime().getTime()) {
			errors.rejectValue("endTime", "endTime", "(*) Thời điểm kết thúc phải lớn hơn thời điểm bắt đầu");
		}
		
		if (flashSales.stream().anyMatch(flashSale -> flashSaleDto.getFlashSaleId() != flashSale.getFlashSaleId() && (flashSaleDto.getEndTime().getTime() >= flashSale.getStartTime().getTime() && flashSaleDto.getEndTime().getTime() <= flashSale.getEndTime().getTime()))) {
			errors.rejectValue("endTime", "endTime", "(*) Mốc thời gian này hiện là thởi điểm đang diễn ra flash sale khác");
		}
		
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleDto.getFlashSaleId()).get();
		List<SaleOff> saleOffs = saleOffService.findAll().stream().
				filter(saleOff -> (flashSale.getStartTime().getTime() >= saleOff.getStartTime().getTime() && flashSale.getStartTime().getTime() <= saleOff.getEndTime().getTime()) || (flashSale.getEndTime().getTime() >= saleOff.getStartTime().getTime() && flashSale.getEndTime().getTime() <= saleOff.getEndTime().getTime())).toList();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().
				filter(flatRateSale -> (flashSale.getStartTime().getTime() >= flatRateSale.getStartTime().getTime() && flashSale.getStartTime().getTime() <= flatRateSale.getEndTime().getTime()) || (flashSale.getEndTime().getTime() >= flatRateSale.getStartTime().getTime() && flashSale.getEndTime().getTime() <= flatRateSale.getEndTime().getTime())).toList();
		if (saleOffs.stream().anyMatch(saleOff -> saleOff.getSaleOffDetails().stream().anyMatch(saleOffDetail -> flashSale.getFlashSaleDetails().stream().anyMatch(flashSaleDetail -> flashSaleDetail.getProductDetail().getProductDetailId().equals(saleOffDetail.getProductDetail().getProductDetailId()))))
				|| flatRateSales.stream().anyMatch(flatRateSale -> flatRateSale.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flashSale.getFlashSaleDetails().stream().anyMatch(flashSaleDetail -> flashSaleDetail.getProductDetail().getProductDetailId().equals(flatRateSaleDetail.getProductDetail().getProductDetailId()))))) {
			errors.rejectValue("startTime", "startTime", "(*) Mốc thời gian này đang được áp dụng trong một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
			errors.rejectValue("endTime", "endTime", "(*) Mốc thời gian này đang được áp dụng trong một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
		}
	}
}

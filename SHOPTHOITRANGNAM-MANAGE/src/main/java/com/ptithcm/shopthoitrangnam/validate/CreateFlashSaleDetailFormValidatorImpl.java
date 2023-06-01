package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDetailDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Component("createFlashSaleDetailFormValidator")
public class CreateFlashSaleDetailFormValidatorImpl implements Validator {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FlashSaleDetailDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		FlashSaleDetailDto flashSaleDetailDto = (FlashSaleDetailDto) target;
		FlashSale flashSale = flashSaleService.findByFlashSaleId(flashSaleDetailDto.getFlashSaleId()).get();
		List<SaleOff> saleOffs = saleOffService.findAll().stream().
				filter(saleOff -> (flashSale.getStartTime().getTime() >= saleOff.getStartTime().getTime() && flashSale.getStartTime().getTime() <= saleOff.getEndTime().getTime()) || (flashSale.getEndTime().getTime() >= saleOff.getStartTime().getTime() && flashSale.getEndTime().getTime() <= saleOff.getEndTime().getTime())).toList();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().
				filter(flatRateSale -> (flashSale.getStartTime().getTime() >= flatRateSale.getStartTime().getTime() && flashSale.getStartTime().getTime() <= flatRateSale.getEndTime().getTime()) || (flashSale.getEndTime().getTime() >= flatRateSale.getStartTime().getTime() && flashSale.getEndTime().getTime() <= flatRateSale.getEndTime().getTime())).toList();
		if (saleOffs.stream().anyMatch(saleOff -> saleOff.getSaleOffDetails().stream().anyMatch(saleOffDetail -> saleOffDetail.getProductDetail().getProductDetailId().equals(flashSaleDetailDto.getProductDetailId())))
				|| flatRateSales.stream().anyMatch(flatRateSale -> flatRateSale.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail().getProductDetailId().equals(flashSaleDetailDto.getProductDetailId())))) {
			errors.rejectValue("productDetailId", "productDetailId", "(*) Sản phẩm này đang được áp dụng trong một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
		}
	}
}

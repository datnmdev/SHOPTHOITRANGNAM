package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDetailDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Component("createSaleOffDetailFormValidator")
public class CreateSaleOffDetailFormValidatorImpl implements Validator {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SaleOffDetailDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		SaleOffDetailDto saleOffDetailDto = (SaleOffDetailDto) target;
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffDetailDto.getSaleOffId()).get();
		List<FlashSale> flashSales = flashSaleService.findAll().stream().
				filter(flashSale -> (saleOff.getStartTime().getTime() >= flashSale.getStartTime().getTime() && saleOff.getStartTime().getTime() <= flashSale.getEndTime().getTime()) || (saleOff.getEndTime().getTime() >= flashSale.getStartTime().getTime() && saleOff.getEndTime().getTime() <= flashSale.getEndTime().getTime())).toList();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().
				filter(flatRateSale -> (saleOff.getStartTime().getTime() >= flatRateSale.getStartTime().getTime() && saleOff.getStartTime().getTime() <= flatRateSale.getEndTime().getTime()) || (saleOff.getEndTime().getTime() >= flatRateSale.getStartTime().getTime() && saleOff.getEndTime().getTime() <= flatRateSale.getEndTime().getTime())).toList();
		if (flashSales.stream().anyMatch(flashSale -> flashSale.getFlashSaleDetails().stream().anyMatch(flashSaleDetail -> flashSaleDetail.getProductDetail().getProductDetailId().equals(saleOffDetailDto.getProductDetailId())))
				|| flatRateSales.stream().anyMatch(flatRateSale -> flatRateSale.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail().getProductDetailId().equals(saleOffDetailDto.getProductDetailId())))) {
			errors.rejectValue("productDetailId", "productDetailId", "(*) Sản phẩm này đang được áp dụng trong một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
		}
	}
}

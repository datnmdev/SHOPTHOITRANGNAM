package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDetailDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Component("createFlatRateSaleDetailFormValidator")
public class CreateFlatRateSaleDetailFormValidatorImpl implements Validator {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FlatRateSaleDetailDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		FlatRateSaleDetailDto flatRateSaleDetailDto = (FlatRateSaleDetailDto) target;
		FlatRateSale flatRateSale = flatRateSaleService.findByFlatRateSaleId(flatRateSaleDetailDto.getFlatRateSaleId()).get();
		List<FlashSale> flashSales = flashSaleService.findAll().stream().
				filter(flashSale -> (flatRateSale.getStartTime().getTime() >= flashSale.getStartTime().getTime() && flatRateSale.getStartTime().getTime() <= flashSale.getEndTime().getTime()) || (flatRateSale.getEndTime().getTime() >= flashSale.getStartTime().getTime() && flatRateSale.getEndTime().getTime() <= flashSale.getEndTime().getTime())).toList();
		List<SaleOff> saleOffs = saleOffService.findAll().stream().
				filter(saleOff -> (flatRateSale.getStartTime().getTime() >= saleOff.getStartTime().getTime() && flatRateSale.getStartTime().getTime() <= saleOff.getEndTime().getTime()) || (flatRateSale.getEndTime().getTime() >= saleOff.getStartTime().getTime() && flatRateSale.getEndTime().getTime() <= saleOff.getEndTime().getTime())).toList();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().
				filter(flatRateSale_ -> flatRateSale_.getFlatRateSaleId() != flatRateSale.getFlatRateSaleId()).
				filter(flatRateSale_ -> (flatRateSale.getStartTime().getTime() >= flatRateSale_.getStartTime().getTime() && flatRateSale.getStartTime().getTime() <= flatRateSale_.getEndTime().getTime()) || (flatRateSale.getEndTime().getTime() >= flatRateSale_.getStartTime().getTime() && flatRateSale.getEndTime().getTime() <= flatRateSale_.getEndTime().getTime())).toList();
		if (flashSales.stream().anyMatch(flashSale -> flashSale.getFlashSaleDetails().stream().anyMatch(flashSaleDetail -> flashSaleDetail.getProductDetail().getProductDetailId().equals(flatRateSaleDetailDto.getProductDetailId())))
				|| saleOffs.stream().anyMatch(saleOff -> saleOff.getSaleOffDetails().stream().anyMatch(saleOffDetail -> saleOffDetail.getProductDetail().getProductDetailId().equals(flatRateSaleDetailDto.getProductDetailId())))
				|| flatRateSales.stream().anyMatch(flatRateSale_ -> flatRateSale_.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail().getProductDetailId().equals(flatRateSaleDetailDto.getProductDetailId())))) {
			errors.rejectValue("productDetailId", "productDetailId", "(*) Sản phẩm này đang được áp dụng trong một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
		}
	}
}

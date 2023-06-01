package com.ptithcm.shopthoitrangnam.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDto;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Component("updateSaleOffFormValidator")
public class UpdateSaleOffFormValidatorImpl implements Validator {
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
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
		
		if (saleOffDto.getEndTime().getTime() <= saleOffDto.getStartTime().getTime()) {
			errors.rejectValue("endTime", "endTime", "(*) Thời điểm kết thúc phải lớn hơn thời điểm bắt đầu");
		}
		
		if (saleOffs.stream().anyMatch(saleOff -> saleOffDto.getSaleOffId() != saleOff.getSaleOffId() && (saleOffDto.getEndTime().getTime() >= saleOff.getStartTime().getTime() && saleOffDto.getEndTime().getTime() <= saleOff.getEndTime().getTime()))) {
			errors.rejectValue("endTime", "endTime", "(*) Mốc thời gian này hiện là thởi điểm đang diễn ra sale off khác");
		}
		
		SaleOff saleOff = saleOffService.findBySaleOffId(saleOffDto.getSaleOffId()).get();
		List<FlashSale> flashSales = flashSaleService.findAll().stream().
				filter(flashSale -> (saleOff.getStartTime().getTime() >= flashSale.getStartTime().getTime() && saleOff.getStartTime().getTime() <= flashSale.getEndTime().getTime()) || (saleOff.getEndTime().getTime() >= flashSale.getStartTime().getTime() && saleOff.getEndTime().getTime() <= flashSale.getEndTime().getTime())).toList();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().
				filter(flatRateSale -> (saleOff.getStartTime().getTime() >= flatRateSale.getStartTime().getTime() && saleOff.getStartTime().getTime() <= flatRateSale.getEndTime().getTime()) || (saleOff.getEndTime().getTime() >= flatRateSale.getStartTime().getTime() && saleOff.getEndTime().getTime() <= flatRateSale.getEndTime().getTime())).toList();
		if (flashSales.stream().anyMatch(flashSale -> flashSale.getFlashSaleDetails().stream().anyMatch(flashSaleDetail -> saleOff.getSaleOffDetails().stream().anyMatch(saleOffDetail -> saleOffDetail.getProductDetail().getProductDetailId().equals(flashSaleDetail.getProductDetail().getProductDetailId()))))
				|| flatRateSales.stream().anyMatch(flatRateSale -> flatRateSale.getFlatRateSaleDetails().stream().anyMatch(flatRateSaleDetail -> saleOff.getSaleOffDetails().stream().anyMatch(saleOffDetail -> saleOffDetail.getProductDetail().getProductDetailId().equals(flatRateSaleDetail.getProductDetail().getProductDetailId()))))) {
			errors.rejectValue("startTime", "startTime", "(*) Mốc thời gian này đang được áp dụng cho một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
			errors.rejectValue("endTime", "endTime", "(*) Mốc thời gian này đang được áp dụng cho một sale khác. Không thể áp dụng nhiều sale cho cùng 1 sản phẩm trong cùng thời điểm áp dụng sale");
		}
	}
}

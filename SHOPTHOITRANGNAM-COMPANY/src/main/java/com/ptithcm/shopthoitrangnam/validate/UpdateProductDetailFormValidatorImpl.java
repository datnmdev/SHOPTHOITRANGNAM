package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.ProductDetailDto;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;

@Component("updateProductDetailFormValidator")
public class UpdateProductDetailFormValidatorImpl implements Validator {
	@Autowired
	ProductDetailService productDetailService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductDetailDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ProductDetailDto productDetailDto = (ProductDetailDto) target;
		String fileExtention = productDetailDto.getImage().substring(productDetailDto.getImage().lastIndexOf(".") + 1);
		if (!fileExtention.equals("jpeg") && !fileExtention.equals("jpg") && 
				!fileExtention.equals("png") && !fileExtention.equals("webp") && !fileExtention.equals("jfif")) {
			errors.rejectValue("image", "image" ,"(*) File tải lên phải có định dạng .png, .jpg, .jpeg, .jfif hoặc webp");
		}
	}
}

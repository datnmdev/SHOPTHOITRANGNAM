package com.ptithcm.shopthoitrangnam.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.ProductCategoryDto;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;
import com.ptithcm.shopthoitrangnam.service.ProductCategoryService;

@Component("createProductCategoryFormValidator")
public class CreateProductCategoryFormValidatorImpl implements Validator {
	@Autowired
	ProductCategoryService productCategoryService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductCategory.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ProductCategoryDto productCategoryDto = (ProductCategoryDto) target;
		if (productCategoryService.findByProductCategoryCode(productCategoryDto.getProductCategoryCode()).isPresent()) {
			errors.rejectValue("productCategoryCode", "productCategoryCode", "(*) Mã loại sản phẩm này đã tồn tại");
		}
	}
}

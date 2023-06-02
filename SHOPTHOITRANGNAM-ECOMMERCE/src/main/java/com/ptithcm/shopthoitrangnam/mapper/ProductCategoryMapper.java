package com.ptithcm.shopthoitrangnam.mapper;

import com.ptithcm.shopthoitrangnam.dto.ProductCategoryDto;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;

public class ProductCategoryMapper {
	public static ProductCategoryDto toProductCategoryDto(ProductCategory productCategory) {
		return new ProductCategoryDto(productCategory.getProductCategoryCode(), productCategory.getProductCategoryName());
	}
}

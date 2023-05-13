package com.ptithcm.shopthoitrangnam.mapper;

import org.springframework.stereotype.Component;

import com.ptithcm.shopthoitrangnam.dto.ProductDto;
import com.ptithcm.shopthoitrangnam.entity.Product;

@Component
public class ProductMapper {
	public static ProductDto toProductDto(Product product) {
		return new ProductDto(
				product.getProductName(), 
				product.getDescription(),
				product.getProductCategory().getProductCategoryCode()
		);
	}
}

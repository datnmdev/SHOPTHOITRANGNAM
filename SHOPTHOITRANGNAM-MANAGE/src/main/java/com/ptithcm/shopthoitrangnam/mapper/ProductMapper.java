package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

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
	
	public static List<ProductDto> toProductDtos(List<Product> products) {
		return products.stream().map(product -> toProductDto(product)).toList();
	}
}

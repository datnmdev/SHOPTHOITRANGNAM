package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.ProductDetailDto;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;

public class ProductDetailMapper {
	public static ProductDetailDto toProductDetailDto(ProductDetail productDetail) {
		return new ProductDetailDto(
				productDetail.getProductDetailId(), productDetail.getProductDetailPK().getProductCode(), 
				productDetail.getProductDetailPK().getSizeCode(), productDetail.getProductDetailPK().getColorCode(), 
				productDetail.getQuantity(), productDetail.getImage()
		);
	}
	
	public static List<ProductDetailDto> productDetailDtos(List<ProductDetail> productDetails) {
		return productDetails.stream().map(productDetail -> toProductDetailDto(productDetail)).toList();
	}
}

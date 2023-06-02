package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.OrderDetailDto;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;

public class OrderDetailMapper {
	public static OrderDetailDto toOrderDetailDto(OrderDetail orderDetail) {
		return new OrderDetailDto(orderDetail.getOrderDetailPK().getOrderCode(), orderDetail.getOrderDetailPK().getProductDetailId(), orderDetail.getQuantity(), orderDetail.getProductRating() != null ? orderDetail.getProductRating().getProductRatingId() : null);
	}
	
	public static List<OrderDetailDto> toOrderDetailDtos(List<OrderDetail> orderDetails) {
		return orderDetails.stream().map(orderDetail -> toOrderDetailDto(orderDetail)).toList();
	}
}

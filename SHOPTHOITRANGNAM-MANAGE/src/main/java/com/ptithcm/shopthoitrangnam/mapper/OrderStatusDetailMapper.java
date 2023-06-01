package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.OrderStatusDetailDto;
import com.ptithcm.shopthoitrangnam.entity.OrderStatusDetail;

public class OrderStatusDetailMapper {
	public static OrderStatusDetailDto toOrderStatusDetailDto(OrderStatusDetail orderStatusDetail) {
		return new OrderStatusDetailDto(orderStatusDetail.getOrderStatusDetailPK().getOrderCode(), orderStatusDetail.getOrderStatusDetailPK().getTransitionTime(), orderStatusDetail.getContent(), orderStatusDetail.getOrderStatus().getOrderStatusCode());
	}
	
	public static List<OrderStatusDetailDto> toOrderStatusDetailDtos(List<OrderStatusDetail> orderStatusDetails) {
		return orderStatusDetails.stream().map(orderStatusDetail -> toOrderStatusDetailDto(orderStatusDetail)).toList();
	}
}

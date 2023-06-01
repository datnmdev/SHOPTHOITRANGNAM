package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.OrderPreparationDetailDto;
import com.ptithcm.shopthoitrangnam.entity.OrderPreparationDetail;

public class OrderPreparationDetailMapper {
	public static OrderPreparationDetailDto toOrderPreparationDetailDto(OrderPreparationDetail orderPreparationDetail) {
		return new OrderPreparationDetailDto(orderPreparationDetail.getOrderPreparationDetailPK().getEmployeeCode(), orderPreparationDetail.getOrderPreparationDetailPK().getOrderCode(), orderPreparationDetail.getTaskDeliveryTime());
	}
	
	public static List<OrderPreparationDetailDto> toPreparationDetailDtos(List<OrderPreparationDetail> orderPreparationDetails) {
		return orderPreparationDetails.stream().map(orderPreparationDetail -> toOrderPreparationDetailDto(orderPreparationDetail)).toList();
	}
}

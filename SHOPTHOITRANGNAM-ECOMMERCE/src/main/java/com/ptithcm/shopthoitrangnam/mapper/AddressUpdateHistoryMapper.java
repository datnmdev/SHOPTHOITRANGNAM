package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.AddressUpdateHistoryDto;
import com.ptithcm.shopthoitrangnam.entity.AddressUpdateHistory;

public class AddressUpdateHistoryMapper {
	public static AddressUpdateHistoryDto toAddressUpdateHistoryDto(AddressUpdateHistory addressUpdateHistory) {
		return  new AddressUpdateHistoryDto(addressUpdateHistory.getAddressUpdateHistoryId(), addressUpdateHistory.getAddressUpdateHistoryPK().getAddressId(), addressUpdateHistory.getAddressUpdateHistoryPK().getUpdateTime(), addressUpdateHistory.getFullName(), addressUpdateHistory.getPhoneNumber(), addressUpdateHistory.getHomeNumber(), addressUpdateHistory.getWard().getWardCode());
	}
	
	public static List<AddressUpdateHistoryDto> toAddressUpdateHistoryDtos(List<AddressUpdateHistory> addressUpdateHistories) {
		return addressUpdateHistories.stream().map(addressUpdateHistory -> toAddressUpdateHistoryDto(addressUpdateHistory)).toList();
	}
}

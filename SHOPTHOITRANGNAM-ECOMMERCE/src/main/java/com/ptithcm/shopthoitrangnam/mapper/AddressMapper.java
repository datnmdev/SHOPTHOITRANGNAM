package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.AddressDto;
import com.ptithcm.shopthoitrangnam.entity.Address;

public class AddressMapper {
	public AddressDto toAddressDto(Address address) {
		return new AddressDto(address.getAddressId(), address.getCustomer().getCustomerCode());
	}
	
	public List<AddressDto> toAddressDtos(List<Address> addresses) {
		return addresses.stream().map(address -> toAddressDto(address)).toList();
	}
}

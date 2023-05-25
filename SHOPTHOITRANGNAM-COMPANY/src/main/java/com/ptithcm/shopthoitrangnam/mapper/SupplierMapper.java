package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.SupplierDto;
import com.ptithcm.shopthoitrangnam.entity.Supplier;

public class SupplierMapper {
	public static SupplierDto toSupplierDto(Supplier supplier) {
		return new SupplierDto(supplier.getSupplierCode(), supplier.getSupplierName(), supplier.getPhoneNumber(), supplier.getEmail(), supplier.getContractStatus().getCode());
	}
	
	public static List<SupplierDto> toSupplierDtos(List<Supplier> suppliers) {
		return suppliers.stream().map(supplier -> toSupplierDto(supplier)).toList();
	}
}

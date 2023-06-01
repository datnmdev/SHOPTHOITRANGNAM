package com.ptithcm.shopthoitrangnam.converter;

import com.ptithcm.shopthoitrangnam.enumeration.ContractStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ContractStatusConverter implements AttributeConverter<ContractStatus, Boolean> {
	@Override
	public Boolean convertToDatabaseColumn(ContractStatus attribute) {
		// TODO Auto-generated method stub
		return attribute.getCode();
	}
	
	@Override
	public ContractStatus convertToEntityAttribute(Boolean dbData) {
		// TODO Auto-generated method stub
		return dbData ? ContractStatus.STILL_VALID : ContractStatus.EXPIRED;
	}
}

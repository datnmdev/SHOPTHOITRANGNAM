package com.ptithcm.shopthoitrangnam.converter;

import com.ptithcm.shopthoitrangnam.enumeration.AccountStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AccountStatusConverter implements AttributeConverter<AccountStatus, Boolean> {
	@Override
	public Boolean convertToDatabaseColumn(AccountStatus attribute) {
		// TODO Auto-generated method stub
		return attribute.getCode();
	}
	
	@Override
	public AccountStatus convertToEntityAttribute(Boolean dbData) {
		// TODO Auto-generated method stub
		return dbData ? AccountStatus.NORMAL : AccountStatus.BLOCKED;
	}
}

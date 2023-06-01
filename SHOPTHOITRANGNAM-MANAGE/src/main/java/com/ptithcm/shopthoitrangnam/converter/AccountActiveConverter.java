package com.ptithcm.shopthoitrangnam.converter;

import com.ptithcm.shopthoitrangnam.enumeration.AccountActivation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AccountActiveConverter implements AttributeConverter<AccountActivation, Boolean> {
	@Override
	public Boolean convertToDatabaseColumn(AccountActivation attribute) {
		// TODO Auto-generated method stub
		return attribute.getCode();
	}
	
	@Override
	public AccountActivation convertToEntityAttribute(Boolean dbData) {
		// TODO Auto-generated method stub
		return dbData ? AccountActivation.ACTIVATED : AccountActivation.NOT_ACTIVATED;
	}
}

package com.ptithcm.shopthoitrangnam.converter;

import com.ptithcm.shopthoitrangnam.enumeration.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, Boolean> {
	@Override
	public Boolean convertToDatabaseColumn(Gender attribute) {
		// TODO Auto-generated method stub
		return attribute.getCode();
	}
	
	@Override
	public Gender convertToEntityAttribute(Boolean dbData) {
		// TODO Auto-generated method stub
		return dbData ? Gender.FEMALE : Gender.MALE;
	}
}

package com.ptithcm.shopthoitrangnam.converter;

import com.ptithcm.shopthoitrangnam.enumeration.JobStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JobStatusConverter implements AttributeConverter<JobStatus, Boolean> {
	@Override
	public Boolean convertToDatabaseColumn(JobStatus attribute) {
		// TODO Auto-generated method stub
		return attribute.getCode();
	}
	
	@Override
	public JobStatus convertToEntityAttribute(Boolean dbData) {
		// TODO Auto-generated method stub
		return dbData ? JobStatus.STILL_WORKING : JobStatus.RESIGNATION;
	}
}

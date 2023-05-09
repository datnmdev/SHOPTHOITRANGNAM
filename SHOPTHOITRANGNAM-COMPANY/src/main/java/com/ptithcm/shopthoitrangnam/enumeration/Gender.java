package com.ptithcm.shopthoitrangnam.enumeration;

public enum Gender {
	MALE(false), FEMALE(true);
	
	private Boolean code;
	
	private Gender(Boolean code) {
		this.code = code;
	}
	
	public Boolean getCode() {
		return code;
	}
}

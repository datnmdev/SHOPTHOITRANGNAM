package com.ptithcm.shopthoitrangnam.enumeration;

public enum Gender {
	MALE(false, "Nam"), FEMALE(true, "Nữ");
	
	private Boolean code;
	private String name;
	
	private Gender(Boolean code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public Boolean getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}

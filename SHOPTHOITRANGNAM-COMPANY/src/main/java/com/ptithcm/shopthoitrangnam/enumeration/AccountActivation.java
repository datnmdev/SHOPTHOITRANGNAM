package com.ptithcm.shopthoitrangnam.enumeration;

public enum AccountActivation {
	ACTIVATED(true), NOT_ACTIVATED(false);
	
	private Boolean code;
	
	private AccountActivation(Boolean code) {
		this.code = code;
	}
	
	public Boolean getCode() {
		return code;
	}
}

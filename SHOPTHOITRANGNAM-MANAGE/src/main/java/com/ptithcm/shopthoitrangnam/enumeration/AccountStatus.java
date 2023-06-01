package com.ptithcm.shopthoitrangnam.enumeration;

public enum AccountStatus {
	BLOCKED(false), NORMAL(true);
	
	private Boolean code;
	
	private AccountStatus(Boolean code) {
		this.code = code;
	}
	
	public Boolean getCode() {
		return code;
	}
}

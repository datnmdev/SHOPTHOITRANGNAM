package com.ptithcm.shopthoitrangnam.enumeration;

public enum ContractStatus {
	STILL_VALID(true), EXPIRED(false);
	
	private Boolean code;
	
	private ContractStatus(Boolean code) {
		this.code = code;
	}
	
	public Boolean getCode() {
		return code;
	}
}

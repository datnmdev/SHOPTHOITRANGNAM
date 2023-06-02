package com.ptithcm.shopthoitrangnam.enumeration;

public enum OrderStatus {
	WAITING("waiting"), CONFIRMED("confirmed"), TRANSPORT("transport"), COMPLETED("completed"), CANCELLED("cancelled");
	
	private String code;
	
	private OrderStatus(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}

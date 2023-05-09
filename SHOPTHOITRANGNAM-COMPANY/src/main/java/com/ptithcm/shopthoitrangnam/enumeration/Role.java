package com.ptithcm.shopthoitrangnam.enumeration;

public enum Role {
	OWNER("owner"), CUSTOMER("customer"), TELLER("teller"), WAREHOUSE_WORKER("warehouseworker");
	
	private String code;
	
	private Role(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
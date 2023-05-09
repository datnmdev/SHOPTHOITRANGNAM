package com.ptithcm.shopthoitrangnam.enumeration;

public enum JobStatus {
	RESIGNATION(false), STILL_WORKING(true);
	
	private Boolean code;
	
	private JobStatus(Boolean code) {
		this.code = code;
	}
	
	public Boolean getCode() {
		return code;
	}
}

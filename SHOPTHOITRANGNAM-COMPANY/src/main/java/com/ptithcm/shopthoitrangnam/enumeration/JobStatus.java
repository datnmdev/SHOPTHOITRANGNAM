package com.ptithcm.shopthoitrangnam.enumeration;

public enum JobStatus {
	RESIGNATION(false, "Đã nghỉ việc"), STILL_WORKING(true, "Đang làm việc");
	
	private Boolean code;
	private String name;
	
	private JobStatus(Boolean code, String name) {
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

package com.ptithcm.shopthoitrangnam.dto;

public class WardDto {
	private String wardCode;
	private String wardName;
	
//	Constructor
	public WardDto() {
		// TODO Auto-generated constructor stub
	}
	
	public WardDto(String wardCode, String wardName) {
		this.wardCode = wardCode;
		this.wardName = wardName;
	}
	
//	Getter and setter methods
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
}

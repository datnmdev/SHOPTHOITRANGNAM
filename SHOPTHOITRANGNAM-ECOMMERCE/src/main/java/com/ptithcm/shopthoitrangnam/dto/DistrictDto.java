package com.ptithcm.shopthoitrangnam.dto;

public class DistrictDto {
	private String districtCode;
	private String districtName;
	
//	Constructor
	public DistrictDto() {
		// TODO Auto-generated constructor stub
	}
	
	public DistrictDto(String districtCode, String districtName) {
		this.districtCode = districtCode;
		this.districtName = districtName;
	}
	
//	Getter and setter methods
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
}

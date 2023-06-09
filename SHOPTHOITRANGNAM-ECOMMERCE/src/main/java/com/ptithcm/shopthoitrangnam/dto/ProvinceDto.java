package com.ptithcm.shopthoitrangnam.dto;

public class ProvinceDto {
	private String provinceCode;
	private String provinceName;
	
//	Constructor
	public ProvinceDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProvinceDto(String provinceCode, String provinceName) {
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
	}

//	Getter and setter methods
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}

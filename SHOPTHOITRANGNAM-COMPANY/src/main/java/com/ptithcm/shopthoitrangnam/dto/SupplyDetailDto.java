package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.NotNull;

public class SupplyDetailDto {
	private String supplierCode;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	private Integer productDetailId;
	
//	Constructor
	public SupplyDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SupplyDetailDto(String supplierCode, Integer productDetailId) {
		this.supplierCode = supplierCode;
		this.productDetailId = productDetailId;
	}
	
//	Getter and setter methods
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public Integer getProductDetailId() {
		return productDetailId;
	}
	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}
}

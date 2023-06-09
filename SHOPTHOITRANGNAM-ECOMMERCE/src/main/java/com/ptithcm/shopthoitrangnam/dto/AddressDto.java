package com.ptithcm.shopthoitrangnam.dto;

public class AddressDto {
	private Integer addressId;
	private String customerCode;
	
//	Constructor
	public AddressDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AddressDto(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public AddressDto(Integer addressId, String customerCode) {
		this.addressId = addressId;
		this.customerCode = customerCode;
	}

//	Getter and setter methods
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
}

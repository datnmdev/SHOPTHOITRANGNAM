package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

public class AddressUpdateHistoryDto {
	private Integer addressUpdateHistoryId;
	private Integer addressId;
	private Date updateTime;
	private String fullName;
	private String phoneNumber;
	private String homeNumber;
	private String wardCode;
	
//	Constructor
	public AddressUpdateHistoryDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AddressUpdateHistoryDto(Integer addressId, Date updateTime, String fullName,
			String phoneNumber, String homeNumber, String wardCode) {
		this.addressId = addressId;
		this.updateTime = updateTime;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.homeNumber = homeNumber;
		this.wardCode = wardCode;
	}
	
	public AddressUpdateHistoryDto(Integer addressUpdateHistoryId, Integer addressId, Date updateTime, String fullName,
			String phoneNumber, String homeNumber, String wardCode) {
		this.addressUpdateHistoryId = addressUpdateHistoryId;
		this.addressId = addressId;
		this.updateTime = updateTime;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.homeNumber = homeNumber;
		this.wardCode = wardCode;
	}

//	Getter and setter methods
	public Integer getAddressUpdateHistoryId() {
		return addressUpdateHistoryId;
	}

	public void setAddressUpdateHistoryId(Integer addressUpdateHistoryId) {
		this.addressUpdateHistoryId = addressUpdateHistoryId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
}

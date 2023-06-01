package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

public class OrderDto {
	private String orderCode;
	
	private Date creationTime;
	
	private Integer addressUpdateHistoryId;
	
	private String customerCode;
	
	private String deliveryNoteCode;
	
//	Constructor
	public OrderDto() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderDto(String orderCode, Date creationTime, Integer addressUpdateHistoryId, String customerCode,
			String deliveryNoteCode) {
		super();
		this.orderCode = orderCode;
		this.creationTime = creationTime;
		this.addressUpdateHistoryId = addressUpdateHistoryId;
		this.customerCode = customerCode;
		this.deliveryNoteCode = deliveryNoteCode;
	}

//	Getter and setter methods
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Integer getAddressUpdateHistoryId() {
		return addressUpdateHistoryId;
	}

	public void setAddressUpdateHistoryId(Integer addressUpdateHistoryId) {
		this.addressUpdateHistoryId = addressUpdateHistoryId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getDeliveryNoteCode() {
		return deliveryNoteCode;
	}

	public void setDeliveryNoteCode(String deliveryNoteCode) {
		this.deliveryNoteCode = deliveryNoteCode;
	}
}

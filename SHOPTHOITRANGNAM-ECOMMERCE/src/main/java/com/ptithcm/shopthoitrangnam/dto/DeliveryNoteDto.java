package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

public class DeliveryNoteDto {
	private String deliveryNoteCode;
	
	private String deliveryNoteName;
	
	private String description;
	
	private Date creationTime;
	
	private String employeeCode;
	
	private String deliveryStaffCode;

//	Constructor
	public DeliveryNoteDto() {
		// TODO Auto-generated constructor stub
	}
	
	public DeliveryNoteDto(String deliveryNoteCode, String deliveryNoteName, String description, Date creationTime,
			String employeeCode, String deliveryStaffCode) {
		this.deliveryNoteCode = deliveryNoteCode;
		this.deliveryNoteName = deliveryNoteName;
		this.description = description;
		this.creationTime = creationTime;
		this.employeeCode = employeeCode;
		this.deliveryStaffCode = deliveryStaffCode;
	}
	
//	Getter and setter methods
	public String getDeliveryNoteCode() {
		return deliveryNoteCode;
	}

	public void setDeliveryNoteCode(String deliveryNoteCode) {
		this.deliveryNoteCode = deliveryNoteCode;
	}

	public String getDeliveryNoteName() {
		return deliveryNoteName;
	}

	public void setDeliveryNoteName(String deliveryNoteName) {
		this.deliveryNoteName = deliveryNoteName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDeliveryStaffCode() {
		return deliveryStaffCode;
	}

	public void setDeliveryStaffCode(String deliveryStaffCode) {
		this.deliveryStaffCode = deliveryStaffCode;
	}
}

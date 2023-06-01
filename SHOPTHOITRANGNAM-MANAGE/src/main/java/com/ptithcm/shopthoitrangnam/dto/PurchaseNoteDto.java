package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

public class PurchaseNoteDto {
	private String purchaseNoteCode;
	
	private String purchaseNoteName;
	
	private String description;
	
	private Date creationTime;
	
	private String employeeCode;
	
	private String purchaseOrderCode;

//	Constructor
	public PurchaseNoteDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PurchaseNoteDto(String purchaseNoteCode, String purchaseNoteName, String description, Date creationTime,
			String employeeCode, String purchaseOrderCode) {
		this.purchaseNoteCode = purchaseNoteCode;
		this.purchaseNoteName = purchaseNoteName;
		this.description = description;
		this.creationTime = creationTime;
		this.employeeCode = employeeCode;
		this.purchaseOrderCode = purchaseOrderCode;
	}

//	Getter and setter methods
	public String getPurchaseNoteCode() {
		return purchaseNoteCode;
	}

	public void setPurchaseNoteCode(String purchaseNoteCode) {
		this.purchaseNoteCode = purchaseNoteCode;
	}

	public String getPurchaseNoteName() {
		return purchaseNoteName;
	}

	public void setPurchaseNoteName(String purchaseNoteName) {
		this.purchaseNoteName = purchaseNoteName;
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

	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}
}

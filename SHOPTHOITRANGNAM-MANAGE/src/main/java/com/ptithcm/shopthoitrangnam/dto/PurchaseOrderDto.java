package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PurchaseOrderDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 50, message = "(*) Mã phiếu đặt không vượt quá 50 ký tự")
	private String purchaseOrderCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Tên phiếu đặt không vượt quá 200 kí tự")
	private String purchaseOrderName;
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date creationTime;
	
	private String supplierCode;
	
	private Boolean status;

//	Constructor
	public PurchaseOrderDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PurchaseOrderDto(String purchaseOrderCode, String purchaseOrderName, String description, Date creationTime,
			String supplierCode, Boolean status) {
		super();
		this.purchaseOrderCode = purchaseOrderCode;
		this.purchaseOrderName = purchaseOrderName;
		this.description = description;
		this.creationTime = creationTime;
		this.supplierCode = supplierCode;
		this.status = status;
	}

//	Getter and setter methods
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}

	public String getPurchaseOrderName() {
		return purchaseOrderName;
	}

	public void setPurchaseOrderName(String purchaseOrderName) {
		this.purchaseOrderName = purchaseOrderName;
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

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}

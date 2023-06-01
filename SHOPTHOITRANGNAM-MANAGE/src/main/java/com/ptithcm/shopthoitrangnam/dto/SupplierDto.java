package com.ptithcm.shopthoitrangnam.dto;

import com.ptithcm.shopthoitrangnam.enumeration.ContractStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SupplierDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 20, message = "(*) Độ dài không vượt quá 20 kí tự")
	private String supplierCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String supplierName;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Pattern(regexp = "0\\d{9}", message = "(*) Số điện thoại không hợp lệ")
	private String phoneNumber;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	@Email(message = "(*) Email không hợp lệ")
	private String email;
	
	private Boolean contractStatus;

//	Constructor
	public SupplierDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SupplierDto(String supplierCode, String supplierName, String phoneNumber, String email,
			Boolean contractStatus) {
		super();
		this.supplierCode = supplierCode;
		this.supplierName = supplierName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.contractStatus = contractStatus;
	}

//	Getter and setter methods
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Boolean contractStatus) {
		this.contractStatus = contractStatus;
	}
}

package com.ptithcm.shopthoitrangnam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOAITAIKHOAN")
public class AccountType {
	@Id
	@Column(name = "MALOAITK")
	private String accountTypeCode;
	
	@Column(name = "TENLOAITK")
	private String accountTypeName;

//	Getter and setter methods
	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
}

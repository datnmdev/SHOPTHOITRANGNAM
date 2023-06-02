package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 100, message = "(*) Độ dài không vượt quá 100 kí tự")
	private String username;
	
	@Size(max = 100, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String password;
	
	@Size(max = 100, message = "(*) Độ dài không vượt quá 100 kí tự")
	private String salt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date creationTime;
	
	private Boolean accountActivation;
	
	private Boolean status;
	
	private String accountTypeCode;

//	Constructor
	public AccountDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AccountDto(String username, String password, String salt, Date creationTime, Boolean accountActivation,
			Boolean status, String accountTypeCode) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.creationTime = creationTime;
		this.accountActivation = accountActivation;
		this.status = status;
		this.accountTypeCode = accountTypeCode;
	}

//	Getter and setter methods
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Boolean getAccountActivation() {
		return accountActivation;
	}

	public void setAccountActivation(Boolean accountActivation) {
		this.accountActivation = accountActivation;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}
}

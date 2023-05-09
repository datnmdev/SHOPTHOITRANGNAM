package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ptithcm.shopthoitrangnam.converter.AccountActiveConverter;
import com.ptithcm.shopthoitrangnam.converter.AccountStatusConverter;
import com.ptithcm.shopthoitrangnam.enumeration.AccountActivation;
import com.ptithcm.shopthoitrangnam.enumeration.AccountStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TAIKHOAN")
public class Account {
	@Id
	@Column(name = "TENDANGNHAP")
	private String username;
	
	@Column(name = "MATKHAU")
	private String password;
	
	@Column(name = "SALT")
	private String salt;
	
	@Column(name = "THOIDIEMTAO")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creationTime;
	
	@Column(name = "DAKICHHOAT")
	@Convert(converter = AccountActiveConverter.class)
	private AccountActivation accountActivation;
	
	@Column(name = "TRANGTHAI")
	@Convert(converter = AccountStatusConverter.class)
	private AccountStatus status;
	
	@OneToOne
	@JoinColumn(name = "MALOAITK")
	private AccountType accountType;
	
//	@OneToOne(mappedBy = "account")
//	private Customer customer;
//	
//	@OneToOne(mappedBy = "account")
//	private Employee employee;

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

	public AccountActivation getAccountActivation() {
		return accountActivation;
	}

	public void setAccountActivation(AccountActivation accountActivation) {
		this.accountActivation = accountActivation;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
}

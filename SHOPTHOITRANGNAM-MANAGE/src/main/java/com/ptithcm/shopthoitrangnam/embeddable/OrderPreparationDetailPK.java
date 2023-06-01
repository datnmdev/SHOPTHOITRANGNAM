package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderPreparationDetailPK implements Serializable {
	private static final long serialVersionUID = -3016439156569341673L;

	@Column(name = "MANV")
	private String employeeCode;
	
	@Column(name = "MADONHANG")
	private String orderCode;

//	Getter and setter methods
	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
}

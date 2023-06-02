package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public class OrderPreparationDetailDto {
	private String employeeCode;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	private String orderCode;
	
	private Date taskDeliveryTime;

//	Constructor
	public OrderPreparationDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderPreparationDetailDto(String employeeCode, String orderCode, Date taskDeliveryTime) {
		super();
		this.employeeCode = employeeCode;
		this.orderCode = orderCode;
		this.taskDeliveryTime = taskDeliveryTime;
	}

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

	public Date getTaskDeliveryTime() {
		return taskDeliveryTime;
	}

	public void setTaskDeliveryTime(Date taskDeliveryTime) {
		this.taskDeliveryTime = taskDeliveryTime;
	}
}

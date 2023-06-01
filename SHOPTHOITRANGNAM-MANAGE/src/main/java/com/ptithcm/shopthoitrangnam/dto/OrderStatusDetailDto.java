package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

public class OrderStatusDetailDto {
	private String orderCode;
	
	private Date transitionTime;
	
	private String content;
	
	private String orderStatusCode;

//	Constructor
	public OrderStatusDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderStatusDetailDto(String orderCode, Date transitionTime, String content, String orderStatusCode) {
		super();
		this.orderCode = orderCode;
		this.transitionTime = transitionTime;
		this.content = content;
		this.orderStatusCode = orderStatusCode;
	}

//	Getter and setter methods
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getTransitionTime() {
		return transitionTime;
	}

	public void setTransitionTime(Date transitionTime) {
		this.transitionTime = transitionTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
}

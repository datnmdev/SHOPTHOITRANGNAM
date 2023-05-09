package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANGTHAIDONHANG")
public class OrderStatus {
	@Id
	@Column(name = "MATRANGTHAI")
	private String orderStatusCode;
	
	@Column(name = "TENTRANGTHAI")
	private String orderStatusName;
	
	@OneToMany(mappedBy = "orderStatus")
	private List<OrderStatusDetail> orderStatusDetails;
	
	@OneToMany(mappedBy = "orderStatus")
	private List<Order> orders;

//	Getter and setter methods
	public String getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public List<OrderStatusDetail> getOrderStatusDetails() {
		return orderStatusDetails;
	}

	public void setOrderStatusDetails(List<OrderStatusDetail> orderStatusDetails) {
		this.orderStatusDetails = orderStatusDetails;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}

package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DONVIVANCHUYEN")
public class ShippingCarrier {
	@Id
	@Column(name = "MADVVC")
	private String shippingCarrierCode;
	
	@Column(name = "TENDVVC")
	private String shippingCarrierName;
	
	@OneToMany(mappedBy = "shippingCarrier")
	private List<Order> order;

//	Getter and setter methods
	public String getShippingCarrierCode() {
		return shippingCarrierCode;
	}

	public void setShippingCarrierCode(String shippingCarrierCode) {
		this.shippingCarrierCode = shippingCarrierCode;
	}

	public String getShippingCarrierName() {
		return shippingCarrierName;
	}

	public void setShippingCarrierName(String shippingCarrierName) {
		this.shippingCarrierName = shippingCarrierName;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
}

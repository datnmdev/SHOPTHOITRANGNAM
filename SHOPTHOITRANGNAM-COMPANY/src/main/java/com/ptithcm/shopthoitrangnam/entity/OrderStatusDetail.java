package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.OrderStatusDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETTRANGTHAIDONHANG")
public class OrderStatusDetail {
	@EmbeddedId
	private OrderStatusDetailPK orderStatusDetailPK;
	
	@MapsId("orderCode")
	@ManyToOne
	@JoinColumn(name = "MADONHANG")
	private Order order;
	
	@Column(name = "MAVANDON", nullable = true)
	private String trackingCode;
	
	@Column(name = "NOIDUNG")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "MATRANGTHAI")
	private OrderStatus orderStatus;

//	Getter and setter methods
	public OrderStatusDetailPK getOrderStatusDetailPK() {
		return orderStatusDetailPK;
	}

	public void setOrderStatusDetailPK(OrderStatusDetailPK orderStatusDetailPK) {
		this.orderStatusDetailPK = orderStatusDetailPK;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
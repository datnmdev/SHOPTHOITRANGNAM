package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ptithcm.shopthoitrangnam.embeddable.OrderStatusDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "CHITIETTRANGTHAIDONHANG")
public class OrderStatusDetail {
	@EmbeddedId
	private OrderStatusDetailPK orderStatusDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MADONHANG", insertable = false, updatable = false)
	private Order order;
	
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
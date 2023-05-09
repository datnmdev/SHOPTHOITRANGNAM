package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "DONHANG")
public class Order {
	@Id
	@Column(name = "MADONHANG")
	private String orderCode;
	
	@Column(name = "THOIDIEMLAP")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creationTime;
	
	@ManyToOne
	@JoinColumn(name = "MATRANGTHAI")
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "MADVVC")
	private ShippingCarrier shippingCarrier;
	
	@OneToOne
	@JoinColumn(name = "SOHD")
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name = "ID_CNDC", referencedColumnName = "ID_CNDC")
	private AddressUpdateHistory addressUpdateHistory;
	
	@OneToOne
	@JoinColumn(name = "MAKH")
	private Customer customer;
	
	@OneToMany(mappedBy = "order")
	private List<OrderStatusDetail> orderStatusDetails;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;

//	Getter and setter methods
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ShippingCarrier getShippingCarrier() {
		return shippingCarrier;
	}

	public void setShippingCarrier(ShippingCarrier shippingCarrier) {
		this.shippingCarrier = shippingCarrier;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public AddressUpdateHistory getAddressUpdateHistory() {
		return addressUpdateHistory;
	}

	public void setAddressUpdateHistory(AddressUpdateHistory addressUpdateHistory) {
		this.addressUpdateHistory = addressUpdateHistory;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderStatusDetail> getOrderStatusDetails() {
		return orderStatusDetails;
	}

	public void setOrderStatusDetails(List<OrderStatusDetail> orderStatusDetails) {
		this.orderStatusDetails = orderStatusDetails;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}

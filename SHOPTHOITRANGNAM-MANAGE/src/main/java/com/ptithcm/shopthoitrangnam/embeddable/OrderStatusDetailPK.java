package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class OrderStatusDetailPK implements Serializable {
	private static final long serialVersionUID = 4685591827716793387L;

	@Column(name = "MADONHANG")
	private String orderCode;
	
	@Column(name = "THOIDIEMCHUYENTIEP")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date transitionTime;

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
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}

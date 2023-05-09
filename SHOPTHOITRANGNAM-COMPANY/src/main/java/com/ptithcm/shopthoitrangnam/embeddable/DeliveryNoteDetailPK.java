package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DeliveryNoteDetailPK implements Serializable {
	private static final long serialVersionUID = 3107346533161090220L;
	
	@Column(name = "MAPHIEUXUAT")
	private String deliveryNoteCode;
	
	@Column(name = "IDCTSP")
	private Integer productDetailId;

//	Getter and setter methods
	public String getDeliveryNoteCode() {
		return deliveryNoteCode;
	}

	public void setDeliveryNoteCode(String deliveryNoteCode) {
		this.deliveryNoteCode = deliveryNoteCode;
	}

	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
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

package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BillDetailPK implements Serializable {
	private static final long serialVersionUID = 4606872241234907208L;

	@Column(name = "SOHD")
	private Long billNumber;
	
	@Column(name = "IDCTSP")
	private Integer productDetailId;

//	Getter and setter methods
	public Long getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
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

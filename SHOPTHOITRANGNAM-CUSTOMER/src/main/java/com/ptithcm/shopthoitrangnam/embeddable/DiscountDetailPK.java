package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DiscountDetailPK implements Serializable {
	private static final long serialVersionUID = 6633063442180901574L;

	@Column(name = "ID_KM")
	private Integer discountId;
	
	@Column(name = "IDCTSP")
	private Integer productDetailId;

//	Getter and setter methods
	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
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

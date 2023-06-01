package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FlatRateSaleDetailPK implements Serializable {
	private static final long serialVersionUID = -1590928822617565693L;
	
	@Column(name = "ID_KM")
	private Integer flatRateSaleId;
	
	@Column(name = "IDCTSP")
	private Integer productDetailId;

//	Getter and setter methods
	public Integer getFlatRateSaleId() {
		return flatRateSaleId;
	}

	public void setFlatRateSaleId(Integer flatRateSaleId) {
		this.flatRateSaleId = flatRateSaleId;
	}

	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}
}

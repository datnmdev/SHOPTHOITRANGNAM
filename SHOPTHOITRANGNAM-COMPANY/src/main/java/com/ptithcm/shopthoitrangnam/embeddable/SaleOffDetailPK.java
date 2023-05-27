package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SaleOffDetailPK implements Serializable {
	private static final long serialVersionUID = -1819744461425325701L;

	@Column(name = "ID_KM")
	private Integer saleOffId;
	
	@Column(name = "IDCTSP")
	private Integer productDetailId;

//	Getter and setter methods
	public Integer getSaleOffId() {
		return saleOffId;
	}

	public void setSaleOffId(Integer saleOffId) {
		this.saleOffId = saleOffId;
	}

	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}
}

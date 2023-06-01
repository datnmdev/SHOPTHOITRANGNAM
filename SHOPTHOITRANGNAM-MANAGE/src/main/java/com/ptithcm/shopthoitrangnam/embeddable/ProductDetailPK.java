package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductDetailPK implements Serializable {
	private static final long serialVersionUID = 1955569755808857365L;

	@Column(name = "MASP")
	private String productCode;
	
	@Column(name = "MAKICHTHUOC")
	private String sizeCode;
	
	@Column(name = "MAMAUSAC")
	private String colorCode;

//	Getter and setter methods
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
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

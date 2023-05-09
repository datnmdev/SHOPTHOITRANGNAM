package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PurchaseNoteDetailPK implements Serializable {
	private static final long serialVersionUID = 6579551717046990652L;

	@Column(name = "MAPHIEUNHAP")
	private String purchaseNoteCode;
	
	@Column(name = "IDCTCC")
	private Integer supplyDetailId;

//	Getter and setter methods
	public String getPurchaseNoteCode() {
		return purchaseNoteCode;
	}

	public void setPurchaseNoteCode(String purchaseNoteCode) {
		this.purchaseNoteCode = purchaseNoteCode;
	}

	public Integer getSupplyDetailId() {
		return supplyDetailId;
	}

	public void setSupplyDetailId(Integer supplyDetailId) {
		this.supplyDetailId = supplyDetailId;
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

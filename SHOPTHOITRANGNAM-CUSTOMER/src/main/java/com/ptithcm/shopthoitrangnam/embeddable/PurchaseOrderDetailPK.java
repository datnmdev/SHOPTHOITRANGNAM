package com.ptithcm.shopthoitrangnam.embeddable;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PurchaseOrderDetailPK implements Serializable {
	private static final long serialVersionUID = -1742169602987320377L;
	
	@Column(name = "MAPHIEUDAT")
	private String purchaseOrderCode;
	
	@Column(name = "IDCTCC")
	private Integer supplyDetailId;

//	Getter and setter methods
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
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

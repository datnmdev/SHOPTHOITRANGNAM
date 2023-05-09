package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.PurchaseNoteDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETPHIEUNHAP")
public class PurchaseNoteDetail {
	@EmbeddedId
	private PurchaseNoteDetailPK purchaseNoteDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MAPHIEUNHAP", insertable=false, updatable=false)
	private PurchaseNote purchaseNote;
	
	@ManyToOne
	@JoinColumn(name = "IDCTCC", referencedColumnName = "IDCTCC", insertable=false, updatable=false)
	private SupplyDetail supplyDetail;
	
	@Column(name = "SOLUONG")
	private Integer quantity;

//	Getter and setter methods
	public PurchaseNoteDetailPK getPurchaseNoteDetailPK() {
		return purchaseNoteDetailPK;
	}

	public void setPurchaseNoteDetailPK(PurchaseNoteDetailPK purchaseNoteDetailPK) {
		this.purchaseNoteDetailPK = purchaseNoteDetailPK;
	}

	public PurchaseNote getPurchaseNote() {
		return purchaseNote;
	}

	public void setPurchaseNote(PurchaseNote purchaseNote) {
		this.purchaseNote = purchaseNote;
	}

	public SupplyDetail getSupplyDetail() {
		return supplyDetail;
	}

	public void setSupplyDetail(SupplyDetail supplyDetail) {
		this.supplyDetail = supplyDetail;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

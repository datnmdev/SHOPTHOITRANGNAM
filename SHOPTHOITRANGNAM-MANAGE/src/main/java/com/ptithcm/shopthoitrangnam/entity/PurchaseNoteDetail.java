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
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
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

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

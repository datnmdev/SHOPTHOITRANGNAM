package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.DeliveryNoteDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETPHIEUXUAT")
public class DeliveryNoteDetail {
	@EmbeddedId
	private DeliveryNoteDetailPK deliveryNoteDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MAPHIEUXUAT", insertable=false, updatable=false)
	private DeliveryNote deliveryNote;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "SOLUONG")
	private Integer quantity;

//	Getter and setter methods
	public DeliveryNoteDetailPK getDeliveryNoteDetailPK() {
		return deliveryNoteDetailPK;
	}

	public void setDeliveryNoteDetailPK(DeliveryNoteDetailPK deliveryNoteDetailPK) {
		this.deliveryNoteDetailPK = deliveryNoteDetailPK;
	}

	public DeliveryNote getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(DeliveryNote deliveryNote) {
		this.deliveryNote = deliveryNote;
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

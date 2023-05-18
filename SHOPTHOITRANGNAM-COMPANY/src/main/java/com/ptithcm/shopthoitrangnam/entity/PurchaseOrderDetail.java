package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.PurchaseOrderDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETPHIEUDAT")
public class PurchaseOrderDetail {
	@EmbeddedId
	private PurchaseOrderDetailPK purchaseOrderDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MAPHIEUDAT", insertable=false, updatable=false)
	private PurchaseOrder purchaseOrder;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "SOLUONG")
	private Integer quantity;
	
//	Getter and setter methods
	public PurchaseOrderDetailPK getPurchaseOrderDetailPK() {
		return purchaseOrderDetailPK;
	}

	public void setPurchaseOrderDetailPK(PurchaseOrderDetailPK purchaseOrderDetailPK) {
		this.purchaseOrderDetailPK = purchaseOrderDetailPK;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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

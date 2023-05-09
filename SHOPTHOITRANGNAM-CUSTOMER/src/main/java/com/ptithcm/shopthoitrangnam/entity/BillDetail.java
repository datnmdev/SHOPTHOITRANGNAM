package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.BillDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETHOADON")
public class BillDetail {
	@EmbeddedId
	private BillDetailPK billDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "SOHD", insertable=false, updatable=false)
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "SOLUONG")
	private Integer quantity;

//	Getter and setter methods
	public BillDetailPK getBillDetailPK() {
		return billDetailPK;
	}

	public void setBillDetailPK(BillDetailPK billDetailPK) {
		this.billDetailPK = billDetailPK;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
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

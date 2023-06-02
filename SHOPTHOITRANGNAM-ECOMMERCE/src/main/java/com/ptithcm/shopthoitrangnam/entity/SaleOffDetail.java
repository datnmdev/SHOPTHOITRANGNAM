package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.SaleOffDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SALEOFFDETAIL")
public class SaleOffDetail {
	@EmbeddedId
	private SaleOffDetailPK saleOffDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "ID_KM", insertable=false, updatable=false)
	private SaleOff saleOff;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "PHANTRAMGIAMGIA")
	private Float saleOffPercentage;

//	Getter and setter methods
	public SaleOffDetailPK getSaleOffDetailPK() {
		return saleOffDetailPK;
	}

	public void setSaleOffDetailPK(SaleOffDetailPK saleOffDetailPK) {
		this.saleOffDetailPK = saleOffDetailPK;
	}

	public SaleOff getSaleOff() {
		return saleOff;
	}

	public void setSaleOff(SaleOff saleOff) {
		this.saleOff = saleOff;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public Float getSaleOffPercentage() {
		return saleOffPercentage;
	}

	public void setSaleOffPercentage(Float saleOffPercentage) {
		this.saleOffPercentage = saleOffPercentage;
	}
}

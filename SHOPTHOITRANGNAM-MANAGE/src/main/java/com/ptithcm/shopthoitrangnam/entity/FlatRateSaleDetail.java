package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.FlatRateSaleDetailPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FLATRATESALEDETAIL")
public class FlatRateSaleDetail {
	@EmbeddedId
	private FlatRateSaleDetailPK flatRateSaleDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "ID_KM", insertable=false, updatable=false)
	private FlatRateSale flatRateSale;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
//	Getter and setter methods
	public FlatRateSaleDetailPK getFlatRateSaleDetailPK() {
		return flatRateSaleDetailPK;
	}

	public void setFlatRateSaleDetailPK(FlatRateSaleDetailPK flatRateSaleDetailPK) {
		this.flatRateSaleDetailPK = flatRateSaleDetailPK;
	}

	public FlatRateSale getFlatRateSale() {
		return flatRateSale;
	}

	public void setFlatRateSale(FlatRateSale flatRateSale) {
		this.flatRateSale = flatRateSale;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
}

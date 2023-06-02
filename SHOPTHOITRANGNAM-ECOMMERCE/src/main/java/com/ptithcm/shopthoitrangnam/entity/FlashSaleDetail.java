package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.FlashSaleDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FLASHSALEDETAIL")
public class FlashSaleDetail {
	@EmbeddedId
	private FlashSaleDetailPK flashSaleDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "ID_KM", insertable=false, updatable=false)
	private FlashSale flashSale;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "PHANTRAMGIAMGIA")
	private Float flashSalePercentage;
	
	@Column(name = "SOLUONGGIOIHAN")
	private Integer limitedQuantity;

//	Getter and setter methods
	public FlashSaleDetailPK getFlashSaleDetailPK() {
		return flashSaleDetailPK;
	}

	public void setFlashSaleDetailPK(FlashSaleDetailPK flashSaleDetailPK) {
		this.flashSaleDetailPK = flashSaleDetailPK;
	}

	public FlashSale getFlashSale() {
		return flashSale;
	}

	public void setFlashSale(FlashSale flashSale) {
		this.flashSale = flashSale;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public Float getFlashSalePercentage() {
		return flashSalePercentage;
	}

	public void setFlashSalePercentage(Float flashSalePercentage) {
		this.flashSalePercentage = flashSalePercentage;
	}

	public Integer getLimitedQuantity() {
		return limitedQuantity;
	}

	public void setLimitedQuantity(Integer limitedQuantity) {
		this.limitedQuantity = limitedQuantity;
	}
}

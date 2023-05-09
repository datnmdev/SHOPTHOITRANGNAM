package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.DiscountDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETKHUYENMAI")
public class DiscountDetail {
	@EmbeddedId
	private DiscountDetailPK discountDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "ID_KM", insertable=false, updatable=false)
	private Discount discount;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "PHANTRAMGIAMGIA")
	private Float discountPercentage;

//	Getter and setter methods
	public DiscountDetailPK getDiscountDetailPK() {
		return discountDetailPK;
	}

	public void setDiscountDetailPK(DiscountDetailPK discountDetailPK) {
		this.discountDetailPK = discountDetailPK;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public Float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
}

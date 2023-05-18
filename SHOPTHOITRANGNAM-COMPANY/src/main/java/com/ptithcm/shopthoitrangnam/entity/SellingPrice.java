package com.ptithcm.shopthoitrangnam.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "GIASANPHAMBANRA")
public class SellingPrice {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sellingPriceId;
	
	@Column(name = "GIA", columnDefinition = "money")
	private BigDecimal price;
	
	@Column(name = "THOIDIEMAPDUNG")
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveTime;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP")
	private ProductDetail productDetail;

//	Getter and setter methods
	public Integer getSellingPriceId() {
		return sellingPriceId;
	}

	public void setSellingPriceId(Integer sellingPriceId) {
		this.sellingPriceId = sellingPriceId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
}

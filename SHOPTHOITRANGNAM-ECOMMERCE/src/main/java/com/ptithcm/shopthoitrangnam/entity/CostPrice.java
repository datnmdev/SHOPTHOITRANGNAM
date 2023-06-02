package com.ptithcm.shopthoitrangnam.entity;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "GIASANPHAMNHAPVAO")
public class CostPrice {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer costPriceId;
	
	@Column(name = "GIA", columnDefinition = "money")
	private BigDecimal price;
	
	@Column(name = "THOIDIEMAPDUNG")
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveTime;
	
	@ManyToOne
	@JoinColumn(name = "IDCTCC", referencedColumnName = "IDCTCC")
	private SupplyDetail supplyDetail;

//	Getter and setter methods
	public Integer getCostPriceId() {
		return costPriceId;
	}

	public void setCostPriceId(Integer costPriceId) {
		this.costPriceId = costPriceId;
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

	public SupplyDetail getSupplyDetail() {
		return supplyDetail;
	}

	public void setSupplyDetail(SupplyDetail supplyDetail) {
		this.supplyDetail = supplyDetail;
	}
}

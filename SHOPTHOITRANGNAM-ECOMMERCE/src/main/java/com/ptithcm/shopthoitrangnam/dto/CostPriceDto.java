package com.ptithcm.shopthoitrangnam.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class CostPriceDto {
	private Integer costPriceId;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DecimalMin("0.0")
	private BigDecimal price;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date effectiveTime;
	
	private Integer supplyDetailId;

//	Constructor
	public CostPriceDto() {
		// TODO Auto-generated constructor stub
	}
	
	public CostPriceDto(Integer costPriceId, BigDecimal price, Date effectiveTime, Integer supplyDetailId) {
		super();
		this.costPriceId = costPriceId;
		this.price = price;
		this.effectiveTime = effectiveTime;
		this.supplyDetailId = supplyDetailId;
	}

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

	public Integer getSupplyDetailId() {
		return supplyDetailId;
	}

	public void setSupplyDetailId(Integer supplyDetailId) {
		this.supplyDetailId = supplyDetailId;
	}
}

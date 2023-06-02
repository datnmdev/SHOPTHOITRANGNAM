package com.ptithcm.shopthoitrangnam.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class SellingPriceDto {
	private Integer sellingPriceId;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DecimalMin("0.0")
	private BigDecimal price;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date effectiveTime;
	
	private Integer productDetailId;
	
//	Constructor
	public SellingPriceDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SellingPriceDto(Integer sellingPriceId, BigDecimal price, Date effectiveTime, Integer productDetailId) {
		this.sellingPriceId = sellingPriceId;
		this.price = price;
		this.effectiveTime = effectiveTime;
		this.productDetailId = productDetailId;
	}
	
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
	public Integer getProductDetailId() {
		return productDetailId;
	}
	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}
	
	
}

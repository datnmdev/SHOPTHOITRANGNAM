package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SaleOffDetailDto {
	private Integer saleOffId;
	
	private Integer productDetailId;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@Min(value = 0, message = "(*) Phần trăm khuyến mãi tối thiểu 0%")
	@Max(value = 100, message = "(*) Phần trăm khuyến mãi tối đa 100%")
	private Float saleOffPercentage;

//	Constructor
	public SaleOffDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SaleOffDetailDto(Integer saleOffId, Integer productDetailId,
			@NotNull(message = "(*) Trường này không được bỏ trống") @Min(value = 0, message = "(*) Phần trăm khuyến mãi tối thiểu 0%") @Max(value = 100, message = "(*) Phần trăm khuyến mãi tối đa 100%") Float saleOffPercentage) {
		super();
		this.saleOffId = saleOffId;
		this.productDetailId = productDetailId;
		this.saleOffPercentage = saleOffPercentage;
	}

//	Getter and setter methods
	public Integer getSaleOffId() {
		return saleOffId;
	}

	public void setSaleOffId(Integer saleOffId) {
		this.saleOffId = saleOffId;
	}

	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}

	public Float getSaleOffPercentage() {
		return saleOffPercentage;
	}

	public void setSaleOffPercentage(Float saleOffPercentage) {
		this.saleOffPercentage = saleOffPercentage;
	}
}

package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FlashSaleDetailDto {
	private Integer flashSaleId;
	
	private Integer productDetailId;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@Min(value = 0, message = "(*) Phần trăm khuyến mãi tối thiểu 0%")
	@Max(value = 100, message = "(*) Phần trăm khuyến mãi tối đa 100%")
	private Float flashSalePercentage;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@Min(value = 1, message = "(*) Số lượng giới hạn tối thiểu là 1")
	private Integer limitedQuantity;

//	Constructor
	public FlashSaleDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public FlashSaleDetailDto(Integer flashSaleId, Integer productDetailId, Float flashSalePercentage,
			Integer limitedQuantity) {
		super();
		this.flashSaleId = flashSaleId;
		this.productDetailId = productDetailId;
		this.flashSalePercentage = flashSalePercentage;
		this.limitedQuantity = limitedQuantity;
	}

//	Getter and setter methods
	public Integer getFlashSaleId() {
		return flashSaleId;
	}

	public void setFlashSaleId(Integer flashSaleId) {
		this.flashSaleId = flashSaleId;
	}

	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
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

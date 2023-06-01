package com.ptithcm.shopthoitrangnam.dto;

public class FlatRateSaleDetailDto {
	private Integer flatRateSaleId;
	
	private Integer productDetailId;
	
//	Constructor
	public FlatRateSaleDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public FlatRateSaleDetailDto(Integer flatRateSaleId, Integer productDetailId) {
		this.flatRateSaleId = flatRateSaleId;
		this.productDetailId = productDetailId;
	}

//	Getter and setter methods
	public Integer getFlatRateSaleId() {
		return flatRateSaleId;
	}

	public void setFlatRateSaleId(Integer flatRateSaleId) {
		this.flatRateSaleId = flatRateSaleId;
	}

	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}
}

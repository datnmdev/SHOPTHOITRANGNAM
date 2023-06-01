package com.ptithcm.shopthoitrangnam.dto;

public class OrderDetailDto {
	private String orderCode;
	
	private Integer productDetailId;
	
	private Integer quantity;
	
	private Long productRatingId;

//	Contructor
	public OrderDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderDetailDto(String orderCode, Integer productDetailId, Integer quantity, Long productRatingId) {
		super();
		this.orderCode = orderCode;
		this.productDetailId = productDetailId;
		this.quantity = quantity;
		this.productRatingId = productRatingId;
	}

//	Getter and setter methods
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductRatingId() {
		return productRatingId;
	}

	public void setProductRatingId(Long productRatingId) {
		this.productRatingId = productRatingId;
	}
}

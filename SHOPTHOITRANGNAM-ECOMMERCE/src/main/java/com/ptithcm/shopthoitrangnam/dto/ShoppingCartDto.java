package com.ptithcm.shopthoitrangnam.dto;

public class ShoppingCartDto {
	private String customerCode;
	
	private Integer productDetailId;
	
	private Integer quantity;
	
//	Constructor
	public ShoppingCartDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ShoppingCartDto(String customerCode, Integer productDetailId, Integer quantity) {
		this.customerCode = customerCode;
		this.productDetailId = productDetailId;
		this.quantity = quantity;
	}

	//	Getter and setter methods
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
}

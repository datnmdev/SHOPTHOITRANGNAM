package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PurchaseOrderDetailDto {
	private String purchaseOrderCode;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	private Integer productDetailId;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@Min(value = 1 ,message = "(*) Số lượng tối thiểu là 1")
	private Integer quantity;

//	Constructor
	public PurchaseOrderDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PurchaseOrderDetailDto(String purchaseOrderCode, Integer productDetailId, Integer quantity) {
		super();
		this.purchaseOrderCode = purchaseOrderCode;
		this.productDetailId = productDetailId;
		this.quantity = quantity;
	}

//	Getter and setter methods
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
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

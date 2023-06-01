package com.ptithcm.shopthoitrangnam.dto;

public class PurchaseNoteDetailDto {
	private String purchaseNoteCode;
	
	private Integer productDetailId;
	
	private Integer quantity;

//	Constructor
	public PurchaseNoteDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PurchaseNoteDetailDto(String purchaseNoteCode, Integer productDetailId, Integer quantity) {
		this.purchaseNoteCode = purchaseNoteCode;
		this.productDetailId = productDetailId;
		this.quantity = quantity;
	}

//	Getter and setter methods
	public String getPurchaseNoteCode() {
		return purchaseNoteCode;
	}

	public void setPurchaseNoteCode(String purchaseNoteCode) {
		this.purchaseNoteCode = purchaseNoteCode;
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

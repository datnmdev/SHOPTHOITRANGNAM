package com.ptithcm.shopthoitrangnam.dto;

public class ProductCategoryDto {
	private String productCategoryCode;
	
	private String productCategoryName;
	
//	Constructor
	public ProductCategoryDto(String productCategoryCode, String productCategoryName) {
		this.productCategoryCode = productCategoryCode;
		this.productCategoryName = productCategoryName;
	}

//	Getter and setter methods
	public String getProductCategoryCode() {
		return productCategoryCode;
	}

	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
}

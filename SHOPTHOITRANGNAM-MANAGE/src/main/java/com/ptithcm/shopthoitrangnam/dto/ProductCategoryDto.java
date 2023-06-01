package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductCategoryDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 20, message = "(*) Độ dài không vượt quá 20 kí tự")
	private String productCategoryCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String productCategoryName;
	
//	Constructor
	public ProductCategoryDto() {
		// TODO Auto-generated constructor stub
	}
	
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

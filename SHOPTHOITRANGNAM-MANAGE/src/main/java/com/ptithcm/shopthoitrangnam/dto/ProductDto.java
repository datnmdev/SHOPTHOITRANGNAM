package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductDto {
	@NotBlank(message = "(*) Tên sản phẩm không được bỏ trống")
	@Size(max = 200, message = "Tên sản phẩm chỉ tối đa 200 kí tự")
	private String productName;
	
	private String description;
	
	@NotBlank(message = "(*) Loại sản phẩm không được bỏ trống")
	private String productCategoryCode;
	
//	Constructor
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDto(String productName, String description, String productCategoryCode) {
		this.productName = productName;
		this.description = description;
		this.productCategoryCode = productCategoryCode;
	}
	
//	Getter and setter methods
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductCategoryCode() {
		return productCategoryCode;
	}
	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}
}

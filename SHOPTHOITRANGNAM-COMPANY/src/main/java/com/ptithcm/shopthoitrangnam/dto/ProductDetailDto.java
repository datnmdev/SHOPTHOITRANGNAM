package com.ptithcm.shopthoitrangnam.dto;

public class ProductDetailDto {
	private Integer productDetailId;
	private String productCode;
	private String sizeCode;
	private String colorCode;
	private Integer quantity;
	private String image;
	
	private String primaryKeyError;
	
//	Constructor
	public ProductDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDetailDto(Integer productDetailId, String productCode, String sizeCode, String colorCode,
			Integer quantity, String image) {
		super();
		this.productDetailId = productDetailId;
		this.productCode = productCode;
		this.sizeCode = sizeCode;
		this.colorCode = colorCode;
		this.quantity = quantity;
		this.image = image;
	}

//	Getter and setter methods
	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrimaryKeyError() {
		return primaryKeyError;
	}

	public void setPrimaryKeyError(String primaryKeyError) {
		this.primaryKeyError = primaryKeyError;
	}
}

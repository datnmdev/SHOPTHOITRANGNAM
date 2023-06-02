package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "SANPHAM")
public class Product {
	@Id
	@Column(name = "MASP")
	private String productCode;
	
	@NotBlank(message = "(*) Tên sản phẩm không được bỏ trống")
	@Size(max = 200, message = "Tên sản phẩm chỉ tối đa 200 kí tự")
	@Column(name = "TENSANPHAM")
	private String productName;
	
	@Column(name = "MOTA", nullable = true)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "MALOAISP")
	private ProductCategory productCategory;
	
	@OneToMany(mappedBy = "product")
	private List<ProductDetail> productDetails;

//	Getter and setter methods
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

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

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}
}

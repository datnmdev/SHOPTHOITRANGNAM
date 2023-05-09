package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOAIKHUYENMAI")
public class DiscountType {
	@Id
	@Column(name = "MALOAIKM")
	private String discountTypeCode;
	
	@Column(name = "TENLOAIKM")
	private String discountTypeName;
	
	@Column(name = "MOTA", nullable = true)
	private String description;
	
	@OneToMany(mappedBy = "discountType")
	private List<Discount> discounts;

//	Getter and setter methods
	public String getDiscountTypeCode() {
		return discountTypeCode;
	}

	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	public String getDiscountTypeName() {
		return discountTypeName;
	}

	public void setDiscountTypeName(String discountTypeName) {
		this.discountTypeName = discountTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}
}

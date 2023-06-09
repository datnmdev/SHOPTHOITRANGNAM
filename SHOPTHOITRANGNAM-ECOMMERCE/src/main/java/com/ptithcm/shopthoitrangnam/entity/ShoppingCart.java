package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.ShoppingCartPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETGIOHANG")
public class ShoppingCart {
	@EmbeddedId
	private ShoppingCartPK shoppingCartPK;
	
	@ManyToOne
	@JoinColumn(name = "MAKH", insertable=false, updatable=false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "SOLUONG")
	private Integer quantity;

//	Getter and setter methods
	public ShoppingCartPK getShoppingCartPK() {
		return shoppingCartPK;
	}

	public void setShoppingCartPK(ShoppingCartPK shoppingCartPK) {
		this.shoppingCartPK = shoppingCartPK;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
}

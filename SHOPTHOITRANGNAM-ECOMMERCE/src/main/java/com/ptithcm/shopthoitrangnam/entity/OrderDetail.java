package com.ptithcm.shopthoitrangnam.entity;

import com.ptithcm.shopthoitrangnam.embeddable.OrderDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETDONHANG")
public class OrderDetail {
	@EmbeddedId
	private OrderDetailPK orderDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MADONHANG", insertable=false, updatable=false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "SOLUONG")
	private Integer quantity;
	
	@OneToOne
	@JoinColumn(name = "ID_DANHGIA")
	private ProductRating productRating;

//	Getter and setter methods
	public OrderDetailPK getOrderDetailPK() {
		return orderDetailPK;
	}

	public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
		this.orderDetailPK = orderDetailPK;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductRating getProductRating() {
		return productRating;
	}

	public void setProductRating(ProductRating productRating) {
		this.productRating = productRating;
	}
}

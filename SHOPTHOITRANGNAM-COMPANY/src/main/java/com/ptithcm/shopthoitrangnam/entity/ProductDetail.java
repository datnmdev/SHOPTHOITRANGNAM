package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.ptithcm.shopthoitrangnam.embeddable.ProductDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETSANPHAM")
public class ProductDetail {
	@NaturalId
	@Column(name = "IDCTSP")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productDetailId;
	
	@EmbeddedId
	private ProductDetailPK productDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MASP", insertable=false, updatable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "MAKICHTHUOC", insertable=false, updatable=false)
	private Size size;
	
	@ManyToOne
	@JoinColumn(name = "MAMAUSAC", insertable=false, updatable=false)
	private Color color;
	
	@Column(name = "SOLUONG")
	private Integer quantity;
	
	@Column(name = "HINHANH")
	private String image;
	
	@OneToMany(mappedBy = "productDetail")
	private List<ShoppingCart> shoppingCarts;
	
	@OneToMany(mappedBy = "productDetail")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "productDetail")
	private List<SellingPrice> sellingPrices;
	
	@OneToMany(mappedBy = "productDetail")
	private List<SupplyDetail> supplyDetails;
	
	@OneToMany(mappedBy = "productDetail")
	private List<DiscountDetail> discountDetails;
	
	@OneToMany(mappedBy = "productDetail")
	private List<PurchaseNoteDetail> purchaseNoteDetails;
	
	@OneToMany(mappedBy = "productDetail")
	private List<PurchaseOrderDetail> purchaseOrderDetails;

//	Getter and setter methods
	public Integer getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Integer productDetailId) {
		this.productDetailId = productDetailId;
	}

	public ProductDetailPK getProductDetailPK() {
		return productDetailPK;
	}

	public void setProductDetailPK(ProductDetailPK productDetailPK) {
		this.productDetailPK = productDetailPK;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<SellingPrice> getSellingPrices() {
		return sellingPrices;
	}

	public void setSellingPrices(List<SellingPrice> sellingPrices) {
		this.sellingPrices = sellingPrices;
	}

	public List<SupplyDetail> getSupplyDetails() {
		return supplyDetails;
	}

	public void setSupplyDetails(List<SupplyDetail> supplyDetails) {
		this.supplyDetails = supplyDetails;
	}

	public List<DiscountDetail> getDiscountDetails() {
		return discountDetails;
	}

	public void setDiscountDetails(List<DiscountDetail> discountDetails) {
		this.discountDetails = discountDetails;
	}

	public List<PurchaseNoteDetail> getPurchaseNoteDetails() {
		return purchaseNoteDetails;
	}

	public void setPurchaseNoteDetails(List<PurchaseNoteDetail> purchaseNoteDetails) {
		this.purchaseNoteDetails = purchaseNoteDetails;
	}

	public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}

	public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}
}

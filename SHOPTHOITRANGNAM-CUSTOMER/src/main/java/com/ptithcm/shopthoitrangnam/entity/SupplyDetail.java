package com.ptithcm.shopthoitrangnam.entity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.ptithcm.shopthoitrangnam.embeddable.SupplyDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETCUNGCAP")
public class SupplyDetail {
	@NaturalId
	@Column(name = "IDCTCC", nullable = true, unique = true)
	private Integer supplyDetailId;
	
	@EmbeddedId
	private SupplyDetailPK supplyDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MANCC", insertable=false, updatable=false)
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@Column(name = "GIA")
	private BigDecimal price;
	
	@OneToMany(mappedBy = "supplyDetail")
	private List<PurchaseNoteDetail> purchaseNoteDetails;
	
	@OneToMany(mappedBy = "supplyDetail")
	private List<PurchaseOrderDetail> purchaseOrderDetails;

//	Getter and setter methods
	public Integer getSupplyDetailId() {
		return supplyDetailId;
	}

	public void setSupplyDetailId(Integer supplyDetailId) {
		this.supplyDetailId = supplyDetailId;
	}

	public SupplyDetailPK getSupplyDetailPK() {
		return supplyDetailPK;
	}

	public void setSupplyDetailPK(SupplyDetailPK supplyDetailPK) {
		this.supplyDetailPK = supplyDetailPK;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

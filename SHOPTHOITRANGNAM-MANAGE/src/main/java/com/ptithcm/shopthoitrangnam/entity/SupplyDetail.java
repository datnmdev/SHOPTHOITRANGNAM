package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.ptithcm.shopthoitrangnam.embeddable.SupplyDetailPK;

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
@Table(name = "CHITIETCUNGCAP")
public class SupplyDetail {
	@NaturalId
	@Column(name = "IDCTCC", nullable = true, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer supplyDetailId;
	
	@EmbeddedId
	private SupplyDetailPK supplyDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MANCC", insertable=false, updatable=false)
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name = "IDCTSP", referencedColumnName = "IDCTSP", insertable=false, updatable=false)
	private ProductDetail productDetail;
	
	@OneToMany(mappedBy = "supplyDetail")
	private List<CostPrice> costPrices;
	
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public List<CostPrice> getCostPrices() {
		return costPrices;
	}

	public void setCostPrices(List<CostPrice> costPrices) {
		this.costPrices = costPrices;
	}
 }

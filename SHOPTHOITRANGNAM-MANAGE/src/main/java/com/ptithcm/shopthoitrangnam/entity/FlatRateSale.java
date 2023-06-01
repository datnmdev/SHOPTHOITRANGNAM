package com.ptithcm.shopthoitrangnam.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "FLATRATESALE")
public class FlatRateSale {
	@Id
	@Column(name = "ID_KM")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flatRateSaleId;
	
	@Column(name = "TENKHUYENMAI")
	private String flatRateSaleName;
	
	@Column(name = "THOIDIEMBATDAT")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date startTime;
	
	@Column(name = "THOIDIEMKETTHUC")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date endTime;

	@Column(name = "DONGGIA", columnDefinition = "money")
	private BigDecimal price;
	
	@OneToMany(mappedBy = "flatRateSale")
	private List<FlatRateSaleDetail> flatRateSaleDetails;

//	Getter and setter methods
	public Integer getFlatRateSaleId() {
		return flatRateSaleId;
	}

	public void setFlatRateSaleId(Integer flatRateSaleId) {
		this.flatRateSaleId = flatRateSaleId;
	}

	public String getFlatRateSaleName() {
		return flatRateSaleName;
	}

	public void setFlatRateSaleName(String flatRateSaleName) {
		this.flatRateSaleName = flatRateSaleName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<FlatRateSaleDetail> getFlatRateSaleDetails() {
		return flatRateSaleDetails;
	}

	public void setFlatRateSaleDetails(List<FlatRateSaleDetail> flatRateSaleDetails) {
		this.flatRateSaleDetails = flatRateSaleDetails;
	}
}

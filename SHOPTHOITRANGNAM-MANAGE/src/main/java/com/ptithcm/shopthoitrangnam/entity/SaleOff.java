package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;
import java.util.List;

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
@Table(name = "SALEOFF")
public class SaleOff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_KM")
	private Integer saleOffId;
	
	@Column(name = "TENKHUYENMAI")
	private String saleOffName;
	
	@Column(name = "THOIDIEMBATDAT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name = "THOIDIEMKETTHUC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@OneToMany(mappedBy = "saleOff")
	private List<SaleOffDetail> saleOffDetails;

//	Getter and setter methods
	public Integer getSaleOffId() {
		return saleOffId;
	}

	public void setSaleOffId(Integer saleOffId) {
		this.saleOffId = saleOffId;
	}

	public String getSaleOffName() {
		return saleOffName;
	}

	public void setSaleOffName(String saleOffName) {
		this.saleOffName = saleOffName;
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

	public List<SaleOffDetail> getSaleOffDetails() {
		return saleOffDetails;
	}

	public void setSaleOffDetails(List<SaleOffDetail> saleOffDetails) {
		this.saleOffDetails = saleOffDetails;
	}
}

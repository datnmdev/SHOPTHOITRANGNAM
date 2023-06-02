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
@Table(name = "FLASHSALE")
public class FlashSale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_KM")
	private Integer flashSaleId;
	
	@Column(name = "TENKHUYENMAI")
	private String flashSaleName;
	
	@Column(name = "THOIDIEMBATDAT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name = "THOIDIEMKETTHUC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@OneToMany(mappedBy = "flashSale")
	private List<FlashSaleDetail> flashSaleDetails;

//	Getter and setter methods
	public Integer getFlashSaleId() {
		return flashSaleId;
	}

	public void setFlashSaleId(Integer flashSaleId) {
		this.flashSaleId = flashSaleId;
	}

	public String getFlashSaleName() {
		return flashSaleName;
	}

	public void setFlashSaleName(String flashSaleName) {
		this.flashSaleName = flashSaleName;
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

	public List<FlashSaleDetail> getFlashSaleDetails() {
		return flashSaleDetails;
	}

	public void setFlashSaleDetails(List<FlashSaleDetail> flashSaleDetails) {
		this.flashSaleDetails = flashSaleDetails;
	}
}

package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "DANHGIASANPHAM")
public class ProductRating {
	@Id
	@Column(name = "ID_DANHGIA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productRatingId;
	
	@Column(name = "THOIDIEMTAO")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creationTime;
	
	@Column(name = "SOSAO")
	private Integer starRating;
	
	@Column(name = "NOIDUNG", nullable = true)
	private String content;
	
//	Getter and setter methods
	public Long getProductRatingId() {
		return productRatingId;
	}

	public void setProductRatingId(Long productRatingId) {
		this.productRatingId = productRatingId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Integer getStarRating() {
		return starRating;
	}

	public void setStarRating(Integer starRating) {
		this.starRating = starRating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

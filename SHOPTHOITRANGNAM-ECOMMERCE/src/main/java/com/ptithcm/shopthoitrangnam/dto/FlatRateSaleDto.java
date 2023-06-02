package com.ptithcm.shopthoitrangnam.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FlatRateSaleDto {
	private Integer flatRateSaleId;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String flatRateSaleName;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date startTime;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date endTime;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@Min(value = 0)
	private BigDecimal price;
	
//	Getter ands setter methods
	public FlatRateSaleDto() {
		// TODO Auto-generated constructor stub
	}
	
	public FlatRateSaleDto(Integer flatRateSaleId, String flatRateSaleName, Date startTime, Date endTime, BigDecimal price) {
		this.flatRateSaleId = flatRateSaleId;
		this.flatRateSaleName = flatRateSaleName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
	}

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
}

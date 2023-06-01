package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SaleOffDto {
	private Integer saleOffId;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String saleOffName;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date startTime;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date endTime;
	
//	Constructor
	public SaleOffDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SaleOffDto(Integer saleOffId, String saleOffName, Date startTime, Date endTime) {
		this.saleOffId = saleOffId;
		this.saleOffName = saleOffName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

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
}

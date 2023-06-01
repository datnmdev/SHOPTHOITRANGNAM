package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FlashSaleDto {
	private Integer flashSaleId;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String flashSaleName;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date startTime;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Future(message = "(*) Thời điểm áp dụng giá phải là mốc thời gian trong tương lai")
	private Date endTime;
	
//	Constructor
	public FlashSaleDto() {
		// TODO Auto-generated constructor stub
	}
	
	public FlashSaleDto(Integer flashSaleId, String flashSaleName, Date startTime, Date endTime) {
		this.flashSaleId = flashSaleId;
		this.flashSaleName = flashSaleName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

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
}

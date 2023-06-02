package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PositionDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 10, message = "(*) Độ dài không vượt quá 10 kí tự")
	private String positionCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String positionName;
	
	private String description;

//	Constructor
	public PositionDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PositionDto(
			@NotBlank(message = "(*) Trường này không được bỏ trống") @Size(max = 10, message = "(*) Độ dài không vượt quá 10 kí tự") String positionCode,
			@NotBlank(message = "(*) Trường này không được bỏ trống") @Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự") String positionName,
			String description) {
		this.positionCode = positionCode;
		this.positionName = positionName;
		this.description = description;
	}

//	Getter and setter methods
	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

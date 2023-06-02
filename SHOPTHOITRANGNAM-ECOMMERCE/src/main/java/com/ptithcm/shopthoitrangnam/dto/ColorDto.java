package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ColorDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 10, message = "(*) Độ dài không vượt quá 10 kí tự")
	private String colorCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String colorName;

//	Constructor
	public ColorDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ColorDto(String colorCode, String colorName) {
		this.colorCode = colorCode;
		this.colorName = colorName;
	}

//	Getter and setter methods
	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
}

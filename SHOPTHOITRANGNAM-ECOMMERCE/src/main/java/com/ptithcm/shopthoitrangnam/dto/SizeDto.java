package com.ptithcm.shopthoitrangnam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SizeDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 10, message = "(*) Độ dài không vượt quá 10 kí tự")
	private String sizeCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "Độ dài không vượt quá 200 kí tự")
	private String sizeName;

//	Constructor
	public SizeDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SizeDto(String sizeCode, String sizeName) {
		super();
		this.sizeCode = sizeCode;
		this.sizeName = sizeName;
	}

//	Getter and setter methods
	public String getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
}

package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 100, message = "(*) Độ dài không vượt quá 100 kí tự")
	private String username;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String password;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 200, message = "(*) Độ dài không vượt quá 200 kí tự")
	private String confirmPassword;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 100, message = "(*) Độ dài không vượt quá 100 kí tự")
	private String firstName;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Size(max = 50, message = "(*) Độ dài không vượt quá 50 kí tự")
	private String lastName;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@Past(message = "(*) Ngày sinh phải là mốc thời gian trong quá khứ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Pattern(regexp = "0\\d{9}", message = "(*) Số điện thoại không hợp lệ")
	private String phoneNumber;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Email(message = "(*) Email không hợp lệ")
	private String email;
	
	@NotBlank(message = "(*) Tỉnh/Thành phố không được bỏ trống")
	private String provinceCode;
	
	@NotBlank(message = "(*) Quận/Huyện không được bỏ trống")
	private String districtCode;
	
	@NotBlank(message = "(*) Phường xã không được bỏ trống")
	private String wardCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	private String specificAddress;

//	Constructor
	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDto(String username, String password, String firstName, String lastName,
			Date dateOfBirth, String phoneNumber, String email, String provinceCode, String districtCode,
			String wardCode) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.provinceCode = provinceCode;
		this.districtCode = districtCode;
		this.wardCode = wardCode;
	}

//	Getter and setter methods
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSpecificAddress() {
		return specificAddress;
	}

	public void setSpecificAddress(String specificAddress) {
		this.specificAddress = specificAddress;
	}
}

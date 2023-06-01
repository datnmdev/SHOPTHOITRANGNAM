package com.ptithcm.shopthoitrangnam.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class EmployeeDto {
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	private String employeeCode;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	private String firstName;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	private String lastName;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@Past(message = "(*) Ngày sinh phải là mốc thời gian ở quá khứ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	private Boolean gender;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Pattern(regexp = "0\\d{9}", message = "(*) Số điện thoại không hợp lệ")
	private String phoneNumber;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	@Email(message = "(*) Email không hợp lệ")
	private String email;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@NotNull(message = "(*) Trường này không được bỏ trống")
	private Boolean jobStatus;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	private String image;
	
	@NotBlank(message = "(*) Trường này không được bỏ trống")
	private String positionCode;
	
	private String username;

//	Constructor
	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeDto(String employeeCode, String firstName, String lastName, Date dateOfBirth, Boolean gender,
			String phoneNumber, String email, Date startDate, Boolean jobStatus, String image, String positionCode, String username) {
		super();
		this.employeeCode = employeeCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.startDate = startDate;
		this.jobStatus = jobStatus;
		this.image = image;
		this.positionCode = positionCode;
		this.username = username;
	}

//	Getter and setter methods
	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
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

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Boolean getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Boolean jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

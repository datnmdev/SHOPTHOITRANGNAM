package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;
import java.util.List;

import com.ptithcm.shopthoitrangnam.converter.GenderConverter;
import com.ptithcm.shopthoitrangnam.converter.JobStatusConverter;
import com.ptithcm.shopthoitrangnam.enumeration.Gender;
import com.ptithcm.shopthoitrangnam.enumeration.JobStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "NHANVIEN")
public class Employee {
	@Id
	@Column(name = "MANV")
	private String employeeCode;
	
	@Column(name = "HO")
	private String firstName;
	
	@Column(name = "TEN")
	private String lastName;
	
	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "GIOITINH")
	@Convert(converter = GenderConverter.class)
	private Gender gender;
	
	@Column(name = "SDT")
	private String phoneNumber;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "NGAYVAOLAM")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "TINHTRANG")
	@Convert(converter = JobStatusConverter.class)
	private JobStatus jobStatus;
	
	@Column(name = "HINHANH")
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "MACHUCVU")
	private Position position;
	
	@OneToOne
	@JoinColumn(name = "TENDANGNHAP", nullable = true)
	private Account account;
	
	@OneToMany(mappedBy = "employee")
	private List<PurchaseNote> purchaseNotes;
	
	@OneToMany(mappedBy = "employee")
	private List<DeliveryNote> creatorDeliveryNotes;
	
	@OneToMany(mappedBy = "deliveryStaff")
	private List<DeliveryNote> deliveryNotes;
	
	@OneToMany(mappedBy = "employee")
	private List<OrderPreparationDetail> orderPreparationDetails;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<PurchaseNote> getPurchaseNotes() {
		return purchaseNotes;
	}

	public void setPurchaseNotes(List<PurchaseNote> purchaseNotes) {
		this.purchaseNotes = purchaseNotes;
	}

	public List<DeliveryNote> getCreatorDeliveryNotes() {
		return creatorDeliveryNotes;
	}

	public void setCreatorDeliveryNotes(List<DeliveryNote> creatorDeliveryNotes) {
		this.creatorDeliveryNotes = creatorDeliveryNotes;
	}

	public List<DeliveryNote> getDeliveryNotes() {
		return deliveryNotes;
	}

	public void setDeliveryNotes(List<DeliveryNote> deliveryNotes) {
		this.deliveryNotes = deliveryNotes;
	}

	public List<OrderPreparationDetail> getOrderPreparationDetails() {
		return orderPreparationDetails;
	}

	public void setOrderPreparationDetails(List<OrderPreparationDetail> orderPreparationDetails) {
		this.orderPreparationDetails = orderPreparationDetails;
	}
}

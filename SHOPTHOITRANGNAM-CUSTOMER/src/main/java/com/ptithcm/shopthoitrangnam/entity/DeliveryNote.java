package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PHIEUXUAT")
public class DeliveryNote {
	@Id
	@Column(name = "MAPHIEUXUAT")
	private String deliveryNoteCode;
	
	@Column(name = "TENPHIEUXUAT")
	private String deliveryNoteName;
	
	@Column(name = "MOTA", nullable = true)
	private String description;
	
	@Column(name = "THOIDIEMLAP")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creationTime;
	
	@ManyToOne
	@JoinColumn(name = "MANV")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "MAKHO")
	private WareHouse wareHouse;
	
	@OneToMany(mappedBy = "deliveryNote")
	private List<DeliveryNoteDetail> deliveryNoteDetails;

//	Getter and setter methods
	public String getDeliveryNoteCode() {
		return deliveryNoteCode;
	}

	public void setDeliveryNoteCode(String deliveryNoteCode) {
		this.deliveryNoteCode = deliveryNoteCode;
	}

	public String getDeliveryNoteName() {
		return deliveryNoteName;
	}

	public void setDeliveryNoteName(String deliveryNoteName) {
		this.deliveryNoteName = deliveryNoteName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public WareHouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(WareHouse wareHouse) {
		this.wareHouse = wareHouse;
	}

	public List<DeliveryNoteDetail> getDeliveryNoteDetails() {
		return deliveryNoteDetails;
	}

	public void setDeliveryNoteDetails(List<DeliveryNoteDetail> deliveryNoteDetails) {
		this.deliveryNoteDetails = deliveryNoteDetails;
	}
}

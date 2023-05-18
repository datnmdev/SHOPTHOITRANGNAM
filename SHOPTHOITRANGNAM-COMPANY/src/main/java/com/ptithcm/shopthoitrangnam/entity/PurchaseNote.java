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
@Table(name = "PHIEUNHAP")
public class PurchaseNote {
	@Id
	@Column(name = "MAPHIEUNHAP")
	private String purchaseNoteCode;
	
	@Column(name = "TENPHIEUNHAP")
	private String purchaseNoteName;
	
	@Column(name = "MOTA", nullable = true)
	private String description;
	
	@Column(name = "THOIDIEMLAP")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creationTime;
	
	@ManyToOne
	@JoinColumn(name = "MANV")
	private Employee employee;
	
	@OneToMany(mappedBy = "purchaseNote")
	private List<PurchaseNoteDetail> purchaseNoteDetails;
	
	@ManyToOne
	@JoinColumn(name = "MANCC")
	private Supplier supplier;

//	Getter and setter methods
	public String getPurchaseNoteCode() {
		return purchaseNoteCode;
	}

	public void setPurchaseNoteCode(String purchaseNoteCode) {
		this.purchaseNoteCode = purchaseNoteCode;
	}

	public String getPurchaseNoteName() {
		return purchaseNoteName;
	}

	public void setPurchaseNoteName(String purchaseNoteName) {
		this.purchaseNoteName = purchaseNoteName;
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

	public List<PurchaseNoteDetail> getPurchaseNoteDetails() {
		return purchaseNoteDetails;
	}

	public void setPurchaseNoteDetails(List<PurchaseNoteDetail> purchaseNoteDetails) {
		this.purchaseNoteDetails = purchaseNoteDetails;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}

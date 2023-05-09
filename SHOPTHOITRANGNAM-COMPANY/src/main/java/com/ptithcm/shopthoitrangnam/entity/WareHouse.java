package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "KHO")
public class WareHouse {
	@Id
	@Column(name = "MAKHO")
	private String wareHouseCode;
	
	@Column(name = "TENKHO")
	private String wareHouseName;
	
	@Column(name = "DIACHI")
	private String address;
	
	@OneToOne
	@JoinColumn(name = "MANVQL")
	private Employee employee;
	
	@OneToMany(mappedBy = "wareHouse")
	private List<PurchaseNote> purchaseNotes;
	
	@OneToMany(mappedBy = "wareHouse")
	private List<DeliveryNote> deliveryNotes;

//	Getter and setter methods
	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<PurchaseNote> getPurchaseNotes() {
		return purchaseNotes;
	}

	public void setPurchaseNotes(List<PurchaseNote> purchaseNotes) {
		this.purchaseNotes = purchaseNotes;
	}

	public List<DeliveryNote> getDeliveryNotes() {
		return deliveryNotes;
	}

	public void setDeliveryNotes(List<DeliveryNote> deliveryNotes) {
		this.deliveryNotes = deliveryNotes;
	}
}

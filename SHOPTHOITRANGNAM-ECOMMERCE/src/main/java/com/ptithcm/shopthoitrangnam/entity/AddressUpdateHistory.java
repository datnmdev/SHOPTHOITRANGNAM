package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.ptithcm.shopthoitrangnam.embeddable.AddressUpdateHistoryPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CAPNHATDIACHI")
public class AddressUpdateHistory {
	@NaturalId
	@Column(name = "ID_CNDC", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressUpdateHistoryId;
	
	@EmbeddedId
	private AddressUpdateHistoryPK addressUpdateHistoryPK;
	
	@ManyToOne
	@JoinColumn(name = "ID_DIACHI", insertable=false, updatable=false)
	private Address address;
	
	@Column(name = "HOTEN")
	private String fullName;
	
	@Column(name = "SDT")
	private String phoneNumber;
	
	@Column(name = "DIACHICUTHE")
	private String homeNumber;
	
	@ManyToOne
	@JoinColumn(name = "MAPHUONG_XA")
	private Ward ward;
	
	@OneToMany(mappedBy = "addressUpdateHistory")
	private List<Order> orders;
	
//	Getter and setter methods
	public Integer getAddressUpdateHistoryId() {
		return addressUpdateHistoryId;
	}

	public void setAddressUpdateHistoryId(Integer addressUpdateHistoryId) {
		this.addressUpdateHistoryId = addressUpdateHistoryId;
	}

	public AddressUpdateHistoryPK getAddressUpdateHistoryPK() {
		return addressUpdateHistoryPK;
	}

	public void setAddressUpdateHistoryPK(AddressUpdateHistoryPK addressUpdateHistoryPK) {
		this.addressUpdateHistoryPK = addressUpdateHistoryPK;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}

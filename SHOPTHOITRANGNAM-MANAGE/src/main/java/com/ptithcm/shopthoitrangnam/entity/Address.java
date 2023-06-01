package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DIACHI")
public class Address {
	@Id
	@Column(name = "ID_DIACHI")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	
	@ManyToOne
	@JoinColumn(name = "MAKH")
	private Customer customer;
	
	@OneToMany(mappedBy = "address")
	private List<AddressUpdateHistory> addressUpdateHistories;

//	Getter and setter methods
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<AddressUpdateHistory> getAddressUpdateHistories() {
		return addressUpdateHistories;
	}

	public void setAddressUpdateHistories(List<AddressUpdateHistory> addressUpdateHistories) {
		this.addressUpdateHistories = addressUpdateHistories;
	}
}
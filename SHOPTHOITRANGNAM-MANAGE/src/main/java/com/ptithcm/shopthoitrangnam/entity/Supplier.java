package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import com.ptithcm.shopthoitrangnam.converter.ContractStatusConverter;
import com.ptithcm.shopthoitrangnam.enumeration.ContractStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "NHACUNGCAP")
public class Supplier {
	@Id
	@Column(name = "MANCC")
	private String supplierCode;
	
	@Column(name = "TENNCC")
	private String supplierName;
	
	@Column(name = "SDT")
	private String phoneNumber;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TINHTRANG")
	@Convert(converter = ContractStatusConverter.class)
	private ContractStatus contractStatus;
	
	@OneToMany(mappedBy = "supplier")
	private List<SupplyDetail> supplyDetails;
	
	@OneToMany(mappedBy = "supplier")
	private List<PurchaseOrder> purchaseOrders;

//	Getter and setter methods
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public List<SupplyDetail> getSupplyDetails() {
		return supplyDetails;
	}

	public void setSupplyDetails(List<SupplyDetail> supplyDetails) {
		this.supplyDetails = supplyDetails;
	}

	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}
}

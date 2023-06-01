package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PHUONG_XA")
public class Ward {
	@Id
	@Column(name = "MAPHUONG_XA")
	private String wardCode;
	
	@Column(name = "TENPHUONG_XA")
	private String wardName;
	
	@ManyToOne
	@JoinColumn(name = "MAQUAN_HUYEN")
	private District district;
	
	@OneToMany(mappedBy = "ward")
	private List<AddressUpdateHistory> addressUpdateHistories;

//	Getter and setter methods
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<AddressUpdateHistory> getAddressUpdateHistories() {
		return addressUpdateHistories;
	}

	public void setAddressUpdateHistories(List<AddressUpdateHistory> addressUpdateHistories) {
		this.addressUpdateHistories = addressUpdateHistories;
	}
}

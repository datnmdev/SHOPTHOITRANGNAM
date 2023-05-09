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
@Table(name = "QUAN_HUYEN")
public class District {
	@Id
	@Column(name = "MAQUAN_HUYEN")
	private String districtCode;
	
	@Column(name = "TENQUAN_HUYEN")
	private String districtName;
	
	@ManyToOne
	@JoinColumn(name = "MATINH_TP")
	private Province province;
	
	@OneToMany(mappedBy = "district")
	private List<Ward> wards;

//	Getter and setter methods
	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Ward> getWards() {
		return wards;
	}

	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}
}

package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TINH_TP")
public class Province {
	@Id
	@Column(name = "MATINH_TP")
	private String provinceCode;
	
	@Column(name = "TENTINH_TP")
	private String provinceName;
	
	@OneToMany(mappedBy = "province")
	private List<District> districts;

//	Getter and setter methods
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}
}

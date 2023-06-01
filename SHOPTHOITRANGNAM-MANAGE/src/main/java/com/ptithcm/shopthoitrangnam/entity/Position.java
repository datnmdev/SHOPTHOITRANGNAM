package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHUCVU")
public class Position {
	@Id
	@Column(name = "MACHUCVU")
	private String positionCode;
	
	@Column(name = "TENCHUCVU")
	private String positionName;
	
	@Column(name = "MOTA", nullable = true)
	private String description;
	
	@OneToMany(mappedBy = "position")
	private List<Employee> employees;

//	Getter and setter methods
	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}

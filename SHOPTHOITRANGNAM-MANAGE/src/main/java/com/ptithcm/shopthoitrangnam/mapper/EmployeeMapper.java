package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.EmployeeDto;
import com.ptithcm.shopthoitrangnam.entity.Employee;

public class EmployeeMapper {
	public static EmployeeDto toEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getEmployeeCode(), employee.getFirstName(), employee.getLastName(), 
				employee.getDateOfBirth(), employee.getGender().getCode(), employee.getPhoneNumber(), 
				employee.getEmail(), employee.getStartDate(), employee.getJobStatus().getCode(), 
				employee.getImage(), employee.getPosition().getPositionCode(), employee.getAccount().getUsername()
		);
	}
	
	public static List<EmployeeDto> toEmployeeDtos(List<Employee> employees) {
		return employees.stream().map(employee -> toEmployeeDto(employee)).toList();
	}
}

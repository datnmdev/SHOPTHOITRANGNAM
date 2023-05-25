package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.EmployeeDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	
	public Optional<Employee> findByEmployeeCode(String employeeCode);
	
	public Optional<Employee> findByAccount(Account account);
	
	public List<Employee> findByEmployeeCodeUsingRegex(String pattern);
	
	public List<Employee> findByEmployeeFullNameUsingRegex(String pattern);
	
	public void deleteByEmployeeCode(String employeeCode);
	
	public void save(EmployeeDto employeeDto);
}

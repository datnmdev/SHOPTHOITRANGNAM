package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.EmployeeDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Employee;
import com.ptithcm.shopthoitrangnam.enumeration.Gender;
import com.ptithcm.shopthoitrangnam.enumeration.JobStatus;
import com.ptithcm.shopthoitrangnam.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	AccountService accountService;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	@Override
	public Optional<Employee> findByEmployeeCode(String employeeCode) {
		return employeeRepository.findByEmployeeCode(employeeCode);
	}
	
	@Override
	public Optional<Employee> findByAccount(Account account) {
		return employeeRepository.findByAccount(account);
	}
	
	@Override
	public List<Employee> findByEmployeeCodeUsingRegex(String pattern) {
		return employeeRepository.findByEmployeeCodeUsingRegex(pattern);
	}
	
	@Override
	public List<Employee> findByEmployeeFullNameUsingRegex(String pattern) {
		return employeeRepository.findByEmployeeFullNameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByEmployeeCode(String employeeCode) {
		employeeRepository.deleteByEmployeeCode(employeeCode);
	}
	
	@Override
	public void save(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setEmployeeCode(employeeDto.getEmployeeCode());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setDateOfBirth(employeeDto.getDateOfBirth());
		employee.setGender(employeeDto.getGender() ? Gender.FEMALE : Gender.MALE);
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmail(employeeDto.getEmail());
		employee.setStartDate(employeeDto.getStartDate());
		employee.setJobStatus(employeeDto.getJobStatus() ? JobStatus.STILL_WORKING : JobStatus.RESIGNATION);
		employee.setPosition(positionService.findByPositionCode(employeeDto.getPositionCode()).get());
		employee.setAccount(accountService.findByUsername(employeeDto.getUsername()).get());
		employee.setImage(employeeDto.getImage());
		
		employeeRepository.save(employee);
	}
}

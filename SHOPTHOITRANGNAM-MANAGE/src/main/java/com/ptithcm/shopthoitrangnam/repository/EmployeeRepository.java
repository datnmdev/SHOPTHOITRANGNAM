package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	public List<Employee> findAll();
	
	public Optional<Employee> findByEmployeeCode(String employeeCode);
	
	public Optional<Employee> findByAccount(Account account);
	
	@Query(value = "SELECT * FROM NHANVIEN WHERE MANV LIKE :pattern", nativeQuery = true)
	public List<Employee> findByEmployeeCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM NHANVIEN WHERE HO + ' ' + TEN LIKE :pattern", nativeQuery = true)
	public List<Employee> findByEmployeeFullNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByEmployeeCode(String employeeCode);
	
	public Employee save(Employee employee);
}

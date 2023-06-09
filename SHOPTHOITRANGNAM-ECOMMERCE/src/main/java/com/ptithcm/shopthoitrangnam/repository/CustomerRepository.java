package com.ptithcm.shopthoitrangnam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	@Query(value = "SELECT dbo.CUSTOMER_CODE()", nativeQuery = true)
	public String getNextCustomerCode();
	
	public Optional<Customer> findByAccount(Account account);
	
	public Optional<Customer> findByCustomerCode(String customerCode);
	
	public Customer save(Customer customer);
}	

package com.ptithcm.shopthoitrangnam.service;

import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.CustomerDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Customer;

public interface CustomerService {
	public String getNextCustomerCode();
	
	public Optional<Customer> findByAccount(Account account);
	
	public Optional<Customer> findByCustomerCode(String customerCode);
	
	public void save(CustomerDto customerDto);
}

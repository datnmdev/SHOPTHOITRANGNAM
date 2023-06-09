package com.ptithcm.shopthoitrangnam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.CustomerDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountService accountService;
	
	@Override
	public String getNextCustomerCode() {
		return customerRepository.getNextCustomerCode();
	}
	
	@Override
	public Optional<Customer> findByAccount(Account account) {
		return customerRepository.findByAccount(account);
	}
	
	@Override
	public Optional<Customer> findByCustomerCode(String customerCode) {
		return customerRepository.findByCustomerCode(customerCode);
	}
	
	@Override
	public void save(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setCustomerCode(getNextCustomerCode());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setDateOfBirth(customerDto.getDateOfBirth());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setEmail(customerDto.getEmail());
		customer.setAccount(accountService.findByUsername(customerDto.getUsername()).get());
		customer.setImage(customerDto.getImage());
		customerRepository.save(customer);
	}
}

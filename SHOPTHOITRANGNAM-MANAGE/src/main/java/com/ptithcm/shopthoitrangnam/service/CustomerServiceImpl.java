package com.ptithcm.shopthoitrangnam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Optional<Customer> findByAccount(Account account) {
		return customerRepository.findByAccount(account);
	}
}

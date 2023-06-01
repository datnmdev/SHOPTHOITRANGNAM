package com.ptithcm.shopthoitrangnam.service;

import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Customer;

public interface CustomerService {
	public Optional<Customer> findByAccount(Account account);
}

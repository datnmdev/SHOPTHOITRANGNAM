package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.Account;

public interface AccountService {
	public List<Account> findAll();
	
	public Optional<Account> findByUsername(String username); 
}

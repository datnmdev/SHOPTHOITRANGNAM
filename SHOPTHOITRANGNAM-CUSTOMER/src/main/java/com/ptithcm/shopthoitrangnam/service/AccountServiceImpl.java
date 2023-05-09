package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	@Override
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
}

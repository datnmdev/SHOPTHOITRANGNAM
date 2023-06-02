package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ptithcm.shopthoitrangnam.dto.AccountDto;
import com.ptithcm.shopthoitrangnam.entity.Account;

public interface AccountService {
	public List<Account> findAll();
	
	public Optional<Account> findByUsername(String username); 
	
	public List<Account> findByUsernameUsingRegex(String pattern);
	
	public void deleteByUsername(String username);
	
	public void insert(AccountDto accountDto);
	
	public void update(AccountDto accountDto);
}

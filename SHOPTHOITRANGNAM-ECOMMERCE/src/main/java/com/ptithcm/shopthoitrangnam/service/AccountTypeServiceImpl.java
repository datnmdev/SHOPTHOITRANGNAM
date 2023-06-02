package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.AccountType;
import com.ptithcm.shopthoitrangnam.repository.AccountTypeRepository;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
	@Autowired
	AccountTypeRepository accountTypeRepository;
	
	@Override
	public List<AccountType> findAll() {
		return accountTypeRepository.findAll();
	}
	
	@Override
	public Optional<AccountType> findByAccountTypeCode(String accountTypeCode) {
		return accountTypeRepository.findByAccountTypeCode(accountTypeCode);
	}
}

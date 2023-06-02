package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.entity.AccountType;

public interface AccountTypeService {
	public List<AccountType> findAll();
	
	public Optional<AccountType> findByAccountTypeCode(String accountTypeCode);
}

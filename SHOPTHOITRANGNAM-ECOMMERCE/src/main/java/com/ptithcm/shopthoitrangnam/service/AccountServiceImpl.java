package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.AccountDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.enumeration.AccountActivation;
import com.ptithcm.shopthoitrangnam.enumeration.AccountStatus;
import com.ptithcm.shopthoitrangnam.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	@Lazy
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	@Override
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
	
	@Override
	public List<Account> findByUsernameUsingRegex(String pattern) {
		return accountRepository.findByUsernameUsingRegex(pattern);
	}
	
	@Override
	public void deleteByUsername(String username) {
		accountRepository.deleteByUsername(username);
	}
	
	@Override
	public void insert(AccountDto accountDto) {
		Account account = new Account();
		account.setUsername(accountDto.getUsername());
		account.setPassword(passwordEncoder.encode(accountDto.getPassword() + accountDto.getSalt()));
		account.setSalt(accountDto.getSalt());
		account.setCreationTime(new Date());;
		account.setAccountActivation(accountDto.getAccountActivation() ? AccountActivation.ACTIVATED : AccountActivation.NOT_ACTIVATED);
		account.setStatus(accountDto.getStatus() ? AccountStatus.NORMAL : AccountStatus.BLOCKED);
		account.setAccountType(accountTypeService.findByAccountTypeCode(accountDto.getAccountTypeCode()).get());
		
		accountRepository.save(account);
	}
	
	@Override
	public void update(AccountDto accountDto) {
		Account oldAccountInfo = accountRepository.findByUsername(accountDto.getUsername()).get();
		
		Account account = new Account();
		account.setUsername(accountDto.getUsername());
		if (accountDto.getPassword().isEmpty()) {
			account.setPassword(oldAccountInfo.getPassword());
			account.setSalt(oldAccountInfo.getSalt());
		} else {
			account.setPassword(passwordEncoder.encode(accountDto.getPassword() + accountDto.getSalt()));
			account.setSalt(accountDto.getSalt());
		}
		account.setCreationTime(accountDto.getCreationTime());;
		account.setAccountActivation(accountDto.getAccountActivation() ? AccountActivation.ACTIVATED : AccountActivation.NOT_ACTIVATED);
		account.setStatus(accountDto.getStatus() ? AccountStatus.NORMAL : AccountStatus.BLOCKED);
		account.setAccountType(accountTypeService.findByAccountTypeCode(accountDto.getAccountTypeCode()).get());
		
		accountRepository.save(account);
	}
}

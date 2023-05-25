package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, String> {
	public List<AccountType> findAll();
	
	public Optional<AccountType> findByAccountTypeCode(String accountTypeCode);
}

package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	public List<Account> findAll();
	
	public Optional<Account> findByUsername(String username); 
}

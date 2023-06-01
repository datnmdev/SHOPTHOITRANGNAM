package com.ptithcm.shopthoitrangnam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	public Optional<Customer> findByAccount(Account account);
}	

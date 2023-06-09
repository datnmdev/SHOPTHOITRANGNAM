package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Address;
import com.ptithcm.shopthoitrangnam.entity.Customer;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	public List<Address> findByCustomer(Customer customer);
	
	public Address save(Address address);
}

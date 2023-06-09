package com.ptithcm.shopthoitrangnam.service;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.AddressDto;
import com.ptithcm.shopthoitrangnam.entity.Address;
import com.ptithcm.shopthoitrangnam.entity.Customer;

public interface AddressService {
	public List<Address> findByCustomer(Customer customer);
	
	public Address save(AddressDto addressDto);
}

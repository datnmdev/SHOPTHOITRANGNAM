package com.ptithcm.shopthoitrangnam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.AddressDto;
import com.ptithcm.shopthoitrangnam.entity.Address;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public List<Address> findByCustomer(Customer customer) {
		return addressRepository.findByCustomer(customer);
	}
	
	@Override
	public Address save(AddressDto addressDto) {
		Address address = new Address();
		address.setCustomer(customerService.findByCustomerCode(addressDto.getCustomerCode()).get());
		return addressRepository.save(address);
	}
}

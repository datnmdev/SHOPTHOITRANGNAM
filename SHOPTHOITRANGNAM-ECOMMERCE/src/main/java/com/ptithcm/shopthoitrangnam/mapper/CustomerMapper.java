package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.CustomerDto;
import com.ptithcm.shopthoitrangnam.entity.Customer;

public class CustomerMapper {
	public static CustomerDto toCustomerDto(Customer customer) {
		return new CustomerDto(customer.getCustomerCode(), customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth(), customer.getPhoneNumber(), customer.getEmail(), customer.getAccount().getUsername(), customer.getImage());
	}
	
	public static List<CustomerDto> toCustomerDtos(List<Customer> customers) {
		return customers.stream().map(customer -> toCustomerDto(customer)).toList();
	}
}

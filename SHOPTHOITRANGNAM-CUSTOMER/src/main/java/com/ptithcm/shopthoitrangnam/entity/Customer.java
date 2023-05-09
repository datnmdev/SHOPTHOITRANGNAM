package com.ptithcm.shopthoitrangnam.entity;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ptithcm.shopthoitrangnam.converter.GenderConverter;
import com.ptithcm.shopthoitrangnam.enumeration.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name =  "KHACHHANG")
public class Customer {
	@Id
	@Column(name = "MAKH")
	private String customerCode;
	
	@Column(name = "HO")
	private String firstName;
	
	@Column(name = "TEN")
	private String lastName;
	
	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String dateOfBirth;
	
	@Column(name = "GIOITINH")
	@Convert(converter = GenderConverter.class)
	private Gender gender;
	
	@Column(name = "SDT")
	private String phoneNumber;
	
	@Column(name = "EMAIL")
	private String email;
	
	@OneToOne
	@JoinColumn(name = "TENDANGNHAP")
	private Account account;
	
	@OneToMany(mappedBy = "customer")
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "customer")
	private List<ShoppingCart> shoppingCarts;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

//	Getter and setter methods
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}

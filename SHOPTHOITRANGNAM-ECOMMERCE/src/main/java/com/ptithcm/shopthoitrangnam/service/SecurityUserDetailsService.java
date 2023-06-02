package com.ptithcm.shopthoitrangnam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.SecurityUserDetails;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(null));
		return new SecurityUserDetails(account);
	}
}

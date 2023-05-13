package com.ptithcm.shopthoitrangnam.configuration;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ptithcm.shopthoitrangnam.entity.SecurityUserDetails;

public class SecurityAuthenticationProvider extends DaoAuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetailsService userDetailsService = getUserDetailsService();
		PasswordEncoder passwordEncoder = getPasswordEncoder();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		if (userDetails == null) {
			throw new BadCredentialsException(null);
		}
		
		if (!passwordEncoder.matches(password + ((SecurityUserDetails) userDetails).getSalt(), userDetails.getPassword())) {
			throw new BadCredentialsException(null);
		}
		return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
	}
}
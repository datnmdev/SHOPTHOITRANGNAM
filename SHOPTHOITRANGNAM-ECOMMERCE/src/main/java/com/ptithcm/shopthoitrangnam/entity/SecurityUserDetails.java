package com.ptithcm.shopthoitrangnam.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ptithcm.shopthoitrangnam.enumeration.AccountActivation;
import com.ptithcm.shopthoitrangnam.enumeration.AccountStatus;
import com.ptithcm.shopthoitrangnam.enumeration.Role;

public class SecurityUserDetails implements UserDetails {
	private static final long serialVersionUID = -5636277443715161381L;
	
	private String username;
	private String password;
	private String salt;
	private Date creationTime;
	private AccountActivation accountActivation;
	private AccountStatus status;
	private AccountType accountType;
	
//	Constructor
	public SecurityUserDetails(Account account) {
		this.username = account.getUsername();
		this.password = account.getPassword();
		this.salt = account.getSalt();
		this.creationTime = account.getCreationTime();
		this.accountActivation = account.getAccountActivation();
		this.status = account.getStatus();
		this.accountType = account.getAccountType();
	}
	
//	Overide UserDetail methods
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return accountActivation.getCode();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : Role.values()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return status.getCode();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
//	Getter and setter methods
	public Date getCreationTime() {
		return creationTime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public AccountActivation getAccountActivation() {
		return accountActivation;
	}

	public void setAccountActivation(AccountActivation accountActivation) {
		this.accountActivation = accountActivation;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

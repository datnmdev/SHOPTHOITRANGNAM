package com.ptithcm.shopthoitrangnam.mapper;

import com.ptithcm.shopthoitrangnam.dto.AccountDto;
import com.ptithcm.shopthoitrangnam.entity.Account;

public class AccountMapper {
	public static AccountDto toAccountDto(Account account) {
		return new AccountDto(
				account.getUsername(), "", "", 
				account.getCreationTime(), account.getAccountActivation().getCode(), 
				account.getStatus().getCode(), account.getAccountType().getAccountTypeCode()
		);
	}
}

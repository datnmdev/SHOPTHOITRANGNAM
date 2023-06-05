package com.ptithcm.shopthoitrangnam.service;

import com.ptithcm.shopthoitrangnam.dto.ShoppingCartDto;
import com.ptithcm.shopthoitrangnam.embeddable.ShoppingCartPK;

public interface ShoppingCartService {
    public void save(ShoppingCartDto shoppingCartDto);
	
	public void deleteByShoppingCartPK(ShoppingCartPK shoppingCartPK);
}

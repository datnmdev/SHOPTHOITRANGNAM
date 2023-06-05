package com.ptithcm.shopthoitrangnam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.ShoppingCartDto;
import com.ptithcm.shopthoitrangnam.embeddable.ShoppingCartPK;
import com.ptithcm.shopthoitrangnam.entity.ShoppingCart;
import com.ptithcm.shopthoitrangnam.repository.ShoppingCartRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Override
	public void save(ShoppingCartDto shoppingCartDto) {
		ShoppingCart shoppingCart = new ShoppingCart();
		ShoppingCartPK shoppingCartPK = new ShoppingCartPK();
		shoppingCartPK.setCustomerCode(shoppingCartDto.getCustomerCode());
		shoppingCartPK.setProductDetailId(shoppingCartDto.getProductDetailId());
		shoppingCart.setShoppingCartPK(shoppingCartPK);
		shoppingCart.setQuantity(shoppingCartDto.getQuantity());
		shoppingCartRepository.save(shoppingCart);
	}
	
	@Override
	public void deleteByShoppingCartPK(ShoppingCartPK shoppingCartPK) {
		shoppingCartRepository.deleteByShoppingCartPK(shoppingCartPK);
	}
}

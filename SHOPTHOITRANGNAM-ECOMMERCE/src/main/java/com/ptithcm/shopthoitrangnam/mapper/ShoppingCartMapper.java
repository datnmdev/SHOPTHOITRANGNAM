package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.ShoppingCartDto;
import com.ptithcm.shopthoitrangnam.entity.ShoppingCart;

public class ShoppingCartMapper {	
	public static ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart) {
		return new ShoppingCartDto(shoppingCart.getShoppingCartPK().getCustomerCode(), shoppingCart.getShoppingCartPK().getProductDetailId(), shoppingCart.getQuantity());
	}
	
	public static List<ShoppingCartDto> toShoppingCartDtos(List<ShoppingCart> shoppingCarts) {
		return  shoppingCarts.stream().map(shoppingCart -> toShoppingCartDto(shoppingCart)).toList();
	}
}

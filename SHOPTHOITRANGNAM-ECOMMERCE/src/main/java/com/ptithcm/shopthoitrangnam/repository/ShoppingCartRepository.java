package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.ShoppingCartPK;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.entity.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartPK> {
	public ShoppingCart save(ShoppingCart shoppingCart);
	
	public void deleteByShoppingCartPK(ShoppingCartPK shoppingCartPK);
}

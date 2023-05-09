package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.ShippingCarrier;

@Repository
public interface ShippingCarrierRepository extends JpaRepository<ShippingCarrier, String> {

}

package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {

}

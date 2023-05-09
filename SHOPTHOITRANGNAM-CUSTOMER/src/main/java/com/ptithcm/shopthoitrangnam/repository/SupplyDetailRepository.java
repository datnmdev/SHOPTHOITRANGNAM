package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;

@Repository
public interface SupplyDetailRepository extends JpaRepository<SupplyDetail, Integer>{

}

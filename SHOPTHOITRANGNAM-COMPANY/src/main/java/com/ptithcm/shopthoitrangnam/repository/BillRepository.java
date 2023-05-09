package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}

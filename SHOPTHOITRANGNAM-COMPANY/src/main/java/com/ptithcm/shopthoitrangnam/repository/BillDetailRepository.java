package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.BillDetailPK;
import com.ptithcm.shopthoitrangnam.entity.BillDetail;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, BillDetailPK>{

}

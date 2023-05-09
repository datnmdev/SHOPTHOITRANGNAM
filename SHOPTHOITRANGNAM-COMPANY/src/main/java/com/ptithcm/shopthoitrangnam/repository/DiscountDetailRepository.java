package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.DiscountDetailPK;
import com.ptithcm.shopthoitrangnam.entity.DiscountDetail;

@Repository
public interface DiscountDetailRepository extends JpaRepository<DiscountDetail, DiscountDetailPK> {

}

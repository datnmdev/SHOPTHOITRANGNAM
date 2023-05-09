package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.OrderDetailPK;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

}

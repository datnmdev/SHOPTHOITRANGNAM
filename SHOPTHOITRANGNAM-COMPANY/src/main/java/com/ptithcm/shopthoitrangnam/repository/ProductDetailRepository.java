package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.ProductDetailPK;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, ProductDetailPK> {

}

package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductRating;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
}

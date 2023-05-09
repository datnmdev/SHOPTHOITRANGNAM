package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {

}

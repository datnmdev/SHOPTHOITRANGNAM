package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.District;
import com.ptithcm.shopthoitrangnam.entity.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {
	public List<Ward> findByDistrict(District district);
	
	public Optional<Ward> findByWardCode(String wardCode);
}

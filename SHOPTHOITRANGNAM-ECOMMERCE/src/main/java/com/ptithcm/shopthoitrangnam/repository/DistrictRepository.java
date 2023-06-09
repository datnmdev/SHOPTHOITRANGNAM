package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.District;
import com.ptithcm.shopthoitrangnam.entity.Province;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
	public List<District> findByProvince(Province province);
	
	public Optional<District> findByDistrictCode(String districtCode);
}

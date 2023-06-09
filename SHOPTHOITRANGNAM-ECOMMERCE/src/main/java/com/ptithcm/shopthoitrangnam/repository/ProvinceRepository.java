package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
	public List<Province> findAll();
	
	public Optional<Province> findByProvinceCode(String provinceCode);
}

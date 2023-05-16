package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {
	public List<Size> findAll();
	
	public Optional<Size> findBySizeCode(String sizeCode);
	
	@Query(value = "SELECT * FROM KICHTHUOC WHERE MAKICHTHUOC LIKE :pattern", nativeQuery = true)
	public List<Size> findBySizeCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM KICHTHUOC WHERE TENKICHTHUOC LIKE :pattern", nativeQuery = true)
	public List<Size> findBySizeNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteBySizeCode(String sizeCode);
	
	public Size save(Size size);
}

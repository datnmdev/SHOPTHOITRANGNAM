package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Position;
import com.ptithcm.shopthoitrangnam.entity.Size;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
	public List<Position> findAll();
	
	public Optional<Position> findByPositionCode(String positionCode);
	
	@Query(value = "SELECT * FROM CHUCVU WHERE MACHUCVU LIKE :pattern", nativeQuery = true)
	public List<Position> findByPositionCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM CHUCVU WHERE TENCHUCVU LIKE :pattern", nativeQuery = true)
	public List<Position> findByPositionNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByPositionCode(String positionCode);
	
	public Position save(Position position);
}

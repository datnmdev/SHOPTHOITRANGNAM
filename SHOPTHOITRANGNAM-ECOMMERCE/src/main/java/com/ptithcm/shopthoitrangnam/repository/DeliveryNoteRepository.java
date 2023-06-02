package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;
import com.ptithcm.shopthoitrangnam.entity.Employee;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, String> {
	public List<DeliveryNote> findAll();
	
	public Optional<DeliveryNote> findByDeliveryNoteCode(String deliveryNoteCode);
	
	public List<DeliveryNote> findByEmployee(Employee employee);
	
	public List<DeliveryNote> findByDeliveryStaff(Employee employee);
	
	@Query(value = "SELECT * FROM PHIEUXUAT WHERE MAPHIEUXUAT LIKE :pattern", nativeQuery = true)
	public List<DeliveryNote> findByDeliveryNoteCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM PHIEUXUAT WHERE TENPHIEUXUAT LIKE :pattern", nativeQuery = true)
	public List<DeliveryNote> findByDeliveryNoteNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByDeliveryNoteCode(String deliveryNoteCode);
	
	public DeliveryNote save(DeliveryNote deliveryNote);
}

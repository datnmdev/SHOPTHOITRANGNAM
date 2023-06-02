package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.dto.ProductCategoryDto;
import com.ptithcm.shopthoitrangnam.entity.ProductCategory;
import com.ptithcm.shopthoitrangnam.entity.Size;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
	public List<ProductCategory> findAll();
	
	public Optional<ProductCategory> findByProductCategoryCode(String productCategoryCode);
	
	@Query(value = "SELECT * FROM LOAISANPHAM WHERE MALOAISP LIKE :pattern", nativeQuery = true)
	public List<ProductCategory> findByProductCategoryCodeUsingRegex(@Param("pattern") String pattern);
	
	@Query(value = "SELECT * FROM LOAISANPHAM WHERE TENLOAISP LIKE :pattern", nativeQuery = true)
	public List<ProductCategory> findByProductCategoryNameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByProductCategoryCode(String sizeCode);
	
	public ProductCategory save(ProductCategory productCategory);
}

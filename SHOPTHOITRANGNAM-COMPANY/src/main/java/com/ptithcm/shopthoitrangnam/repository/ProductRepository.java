package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	@Query(value = "SELECT dbo.PRODUCT_CODE(:productTypeCode)", nativeQuery = true)
	public String getNextProductCode(@Param("productTypeCode") String productTypeCode);
	
	public List<Product> findAll();
	
	public Page<Product> findAll(Pageable pageble);
	
	public Optional<Product> findByProductCode(String productCode);
	
	@Query(value = "SELECT * FROM SANPHAM WHERE TENSANPHAM LIKE N'%?1%'", nativeQuery = true)
	public List<Product> findByProductNameUsingRegex(String productName);
	
	@Query(value = "SELECT * FROM SANPHAM WHERE MALOAISP LIKE N'%?1%'", nativeQuery = true)
	public List<Product> findByProductCategoryCodeUsingRegex(String productCategoryCode);
	
	public void deleteByProductCode(String productCode);
	
	public Product save(Product product);
}

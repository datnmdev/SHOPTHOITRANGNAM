package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	public List<Account> findAll();
	
	public Optional<Account> findByUsername(String username); 
	
	@Query(value = "SELECT * FROM TAIKHOAN WHERE TENDANGNHAP LIKE :pattern", nativeQuery = true)
	public List<Account> findByUsernameUsingRegex(@Param("pattern") String pattern);
	
	public void deleteByUsername(String username);
	
	public Account save(Account account);
}

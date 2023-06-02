package com.ptithcm.shopthoitrangnam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.PurchaseNoteDetailPK;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNoteDetail;

@Repository
public interface PurchaseNoteDetailRepository extends JpaRepository<PurchaseNoteDetail, PurchaseNoteDetailPK> {
	public List<PurchaseNoteDetail> findAll();
	
	public Optional<PurchaseNoteDetail> findByPurchaseNoteDetailPK(PurchaseNoteDetailPK purchaseNoteDetailPK);
	
	public List<PurchaseNoteDetail> findByPurchaseNote(PurchaseNote purchaseNote);
	
	public void deleteByPurchaseNoteDetailPK(PurchaseNoteDetailPK purchaseNoteDetailPK);
	
	public void deleteByPurchaseNote(PurchaseNote purchaseNote);
	
	public PurchaseNoteDetail save(PurchaseNoteDetail purchaseNoteDetail);
}

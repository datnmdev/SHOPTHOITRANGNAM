package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.PurchaseNoteDetailPK;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNoteDetail;

public interface PurchaseNoteDetailService {
	public List<PurchaseNoteDetail> findAll();
	
	public Optional<PurchaseNoteDetail> findByPurchaseNoteDetailPK(PurchaseNoteDetailPK purchaseNoteDetailPK);
	
	public List<PurchaseNoteDetail> findByPurchaseNote(PurchaseNote purchaseNote);
	
	public void deleteByPurchaseNoteDetailPK(PurchaseNoteDetailPK purchaseNoteDetailPK);
	
	public void deleteByPurchaseNote(PurchaseNote purchaseNote);
	
	public void save(PurchaseNoteDetailDto purchaseNoteDetailDto);
}

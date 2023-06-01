package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.PurchaseNoteDetailPK;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNoteDetail;
import com.ptithcm.shopthoitrangnam.repository.PurchaseNoteDetailRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchaseNoteDetailServiceImpl implements PurchaseNoteDetailService {
	@Autowired
	PurchaseNoteDetailRepository purchaseNoteDetailRepository;
	
	@Override
	public List<PurchaseNoteDetail> findAll() {
		return purchaseNoteDetailRepository.findAll();
	}
	
	@Override
	public Optional<PurchaseNoteDetail> findByPurchaseNoteDetailPK(PurchaseNoteDetailPK purchaseNoteDetailPK) {
		return purchaseNoteDetailRepository.findByPurchaseNoteDetailPK(purchaseNoteDetailPK);
	}
	
	@Override
	public List<PurchaseNoteDetail> findByPurchaseNote(PurchaseNote purchaseNote) {
		return purchaseNoteDetailRepository.findByPurchaseNote(purchaseNote);
	}
	
	@Override
	public void deleteByPurchaseNoteDetailPK(PurchaseNoteDetailPK purchaseNoteDetailPK) {
		purchaseNoteDetailRepository.deleteByPurchaseNoteDetailPK(purchaseNoteDetailPK);
	}
	
	@Override
	public void deleteByPurchaseNote(PurchaseNote purchaseNote) {
		purchaseNoteDetailRepository.deleteByPurchaseNote(purchaseNote);
	}
	
	@Override
	public void save(PurchaseNoteDetailDto purchaseNoteDetailDto) {
		PurchaseNoteDetail purchaseNoteDetail = new PurchaseNoteDetail();
		PurchaseNoteDetailPK purchaseNoteDetailPK = new PurchaseNoteDetailPK();
		purchaseNoteDetailPK.setPurchaseNoteCode(purchaseNoteDetailDto.getPurchaseNoteCode());
		purchaseNoteDetailPK.setProductDetailId(purchaseNoteDetailDto.getProductDetailId());
		purchaseNoteDetail.setPurchaseNoteDetailPK(purchaseNoteDetailPK);
		purchaseNoteDetail.setQuantity(purchaseNoteDetailDto.getQuantity());
		purchaseNoteDetailRepository.save(purchaseNoteDetail);
	}
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.FlatRateSaleDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.FlatRateSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;
import com.ptithcm.shopthoitrangnam.repository.FlatRateSaleDetailRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlatRateSaleDetailServiceImp implements FlatRateSaleDetailService {
	@Autowired
	FlatRateSaleDetailRepository flatRateSaleDetailRepository;
	
	@Override
	public List<FlatRateSaleDetail> findAll() {
		return flatRateSaleDetailRepository.findAll();
	}
	
	@Override
	public Optional<FlatRateSaleDetail> findByFlatRateSaleDetailPK(FlatRateSaleDetailPK flatRateSaleDetailPK) {
		return flatRateSaleDetailRepository.findByFlatRateSaleDetailPK(flatRateSaleDetailPK);
	}
	
	@Override
	public List<FlatRateSaleDetail> findByFlatRateSale(FlatRateSale flatRateSale) {
		return flatRateSaleDetailRepository.findByFlatRateSale(flatRateSale);
	}
	
	@Override
	public void deleteByFlatRateSaleDetailPK(FlatRateSaleDetailPK flatRateSaleDetailPK) {
		flatRateSaleDetailRepository.deleteByFlatRateSaleDetailPK(flatRateSaleDetailPK);
	}
	
	@Override
	public void deleteAllByFlatRateSale(FlatRateSale flatRateSale) {
		flatRateSaleDetailRepository.deleteAllByFlatRateSale(flatRateSale);
	}
	
	@Override
	public void save(FlatRateSaleDetailDto flatRateSaleDetailDto) {
		FlatRateSaleDetail flatRateSaleDetail = new FlatRateSaleDetail();
		FlatRateSaleDetailPK flatRateSaleDetailPK = new FlatRateSaleDetailPK();
		flatRateSaleDetailPK.setFlatRateSaleId(flatRateSaleDetailDto.getFlatRateSaleId());
		flatRateSaleDetailPK.setProductDetailId(flatRateSaleDetailDto.getProductDetailId());
		flatRateSaleDetail.setFlatRateSaleDetailPK(flatRateSaleDetailPK);
		flatRateSaleDetailRepository.save(flatRateSaleDetail);
	}
}

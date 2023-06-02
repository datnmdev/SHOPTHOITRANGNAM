package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.FlashSaleDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.FlashSaleDetailPK;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;
import com.ptithcm.shopthoitrangnam.repository.FlashSaleDetailRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlashSaleDetailServiceImpl implements FlashSaleDetailService {
	@Autowired
	FlashSaleDetailRepository flashSaleDetailRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<FlashSaleDetail> findAll() {
		return flashSaleDetailRepository.findAll();
	}
	
	@Override
	public Optional<FlashSaleDetail> findByFlashSaleDetailPK(FlashSaleDetailPK flashSaleDetailPK) {
		return flashSaleDetailRepository.findByFlashSaleDetailPK(flashSaleDetailPK);
	}
	
	@Override
	public List<FlashSaleDetail> findByFlashSale(FlashSale flashSale) {
		return flashSaleDetailRepository.findByFlashSale(flashSale);
	}
	
	@Override
	public void deleteByFlashSaleDetailPK(FlashSaleDetailPK flashSaleDetailPK) {
		flashSaleDetailRepository.deleteByFlashSaleDetailPK(flashSaleDetailPK);
	}
	
	@Override
	public void deleteAllByFlashSale(FlashSale flashSale) {
		flashSaleDetailRepository.deleteAllByFlashSale(flashSale);
	}
	
	@Override
	public void update(FlashSaleDetailDto flashSaleDetailDto) {
		String sql = "UPDATE FLASHSALEDETAIL SET PHANTRAMGIAMGIA = ?, SOLUONGGIOIHAN = ? WHERE ID_KM = ? AND IDCTSP = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, flashSaleDetailDto.getFlashSalePercentage());
		query.setParameter(2, flashSaleDetailDto.getLimitedQuantity());
		query.setParameter(3, flashSaleDetailDto.getFlashSaleId());
		query.setParameter(4, flashSaleDetailDto.getProductDetailId());
		query.executeUpdate();
	}
	
	@Override
	public void save(FlashSaleDetailDto flashSaleDetailDto) {
		FlashSaleDetail flashSaleDetail = new FlashSaleDetail();
		FlashSaleDetailPK flashSaleDetailPK = new FlashSaleDetailPK();
		flashSaleDetailPK.setFlashSaleId(flashSaleDetailDto.getFlashSaleId());
		flashSaleDetailPK.setProductDetailId(flashSaleDetailDto.getProductDetailId());
		flashSaleDetail.setFlashSaleDetailPK(flashSaleDetailPK);
		flashSaleDetail.setFlashSalePercentage(flashSaleDetailDto.getFlashSalePercentage());
		flashSaleDetail.setLimitedQuantity(flashSaleDetailDto.getLimitedQuantity());
		flashSaleDetailRepository.save(flashSaleDetail);
	}
}

package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.SaleOffDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.SaleOffDetailPK;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;
import com.ptithcm.shopthoitrangnam.repository.SaleOffDetailRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SaleOffDetailServiceImpl implements SaleOffDetailService {
	@Autowired
	SaleOffDetailRepository saleOffDetailRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<SaleOffDetail> findAll() {
		return saleOffDetailRepository.findAll();
	}
	
	@Override
	public Optional<SaleOffDetail> findBySaleOffDetailPK(SaleOffDetailPK saleOffDetailPK) {
		return saleOffDetailRepository.findById(saleOffDetailPK);
	}
	
	@Override
	public List<SaleOffDetail> findBySaleOff(SaleOff saleOff) {
		return saleOffDetailRepository.findBySaleOff(saleOff);
	}
	
	@Override
	public void deleteBySaleOffDetailPK(SaleOffDetailPK saleOffDetailPK) {
		saleOffDetailRepository.deleteBySaleOffDetailPK(saleOffDetailPK);
	}
	
	@Override
	public void deleteAllBySaleOff(SaleOff saleOff) {
		saleOffDetailRepository.deleteAllBySaleOff(saleOff);
	}
	
	@Override
	public void update(SaleOffDetailDto saleOffDetailDto) {
		String sql = "UPDATE SALEOFFDETAIL SET PHANTRAMGIAMGIA = ? WHERE ID_KM = ? AND IDCTSP = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, saleOffDetailDto.getSaleOffPercentage());
		query.setParameter(2, saleOffDetailDto.getSaleOffId());
		query.setParameter(3, saleOffDetailDto.getProductDetailId());
		query.executeUpdate();
	}
	
	@Override
	public void save(SaleOffDetailDto saleOffDetailDto) {
		SaleOffDetail saleOffDetail = new SaleOffDetail();
		SaleOffDetailPK saleOffDetailPK = new SaleOffDetailPK();
		saleOffDetailPK.setSaleOffId(saleOffDetailDto.getSaleOffId());
		saleOffDetailPK.setProductDetailId(saleOffDetailDto.getProductDetailId());
		saleOffDetail.setSaleOffDetailPK(saleOffDetailPK);
		saleOffDetail.setSaleOffPercentage(saleOffDetailDto.getSaleOffPercentage());
		saleOffDetailRepository.save(saleOffDetail);
	}
}

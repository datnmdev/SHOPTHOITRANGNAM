package com.ptithcm.shopthoitrangnam.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.CostPriceDto;
import com.ptithcm.shopthoitrangnam.entity.CostPrice;
import com.ptithcm.shopthoitrangnam.entity.Supplier;
import com.ptithcm.shopthoitrangnam.entity.SupplyDetail;
import com.ptithcm.shopthoitrangnam.repository.CostPriceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CostPriceServiceImpl implements CostPriceService {
	@Autowired
	CostPriceRepository costPriceRepository;
	
	@Autowired
	SupplyDetailService supplyDetailService;
	
	@Override
	public List<CostPrice> findAll() {
		return costPriceRepository.findAll();
	}
	
	@Override
	public Optional<CostPrice> findByCostPriceId(Integer costPriceId) {
		return costPriceRepository.findByCostPriceId(costPriceId);
	}
	
	@Override
	public List<CostPrice> findBySupplyDetail(SupplyDetail supplyDetail) {
		return costPriceRepository.findBySupplyDetail(supplyDetail);
	}
	
	@Override
	public List<CostPrice> findBySupplyDetailAndEffectiveTime(SupplyDetail supplyDetail, Date effectiveTime) {
		return costPriceRepository.findBySupplyDetailAndEffectiveTime(supplyDetail, effectiveTime);
	}
	
	@Override
	public void deleteByCostPriceId(Integer costPriceId) {
		costPriceRepository.deleteByCostPriceId(costPriceId);
	}
	
	@Override
	public void deleteBySupplyDetail(SupplyDetail supplyDetail) {
		costPriceRepository.deleteBySupplyDetail(supplyDetail);
	}
	
	@Override
	public void deleteAllBySupplier(Supplier supplier) {
		List<SupplyDetail> supplyDetails = supplier.getSupplyDetails();
		for (SupplyDetail supplyDetail : supplyDetails) {
			deleteBySupplyDetail(supplyDetail);
		}
	}
	
	@Override
	public void save(CostPriceDto costPriceDto) {
		CostPrice costPrice = new CostPrice();
		costPrice.setCostPriceId(costPriceDto.getCostPriceId());
		costPrice.setPrice(costPriceDto.getPrice());
		costPrice.setEffectiveTime(costPriceDto.getEffectiveTime());
		costPrice.setSupplyDetail(supplyDetailService.findBySupplyDetailId(costPriceDto.getSupplyDetailId()).get());
		costPriceRepository.save(costPrice);
	}
}

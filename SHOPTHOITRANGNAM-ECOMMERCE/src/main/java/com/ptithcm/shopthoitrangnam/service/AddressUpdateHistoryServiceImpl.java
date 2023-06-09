package com.ptithcm.shopthoitrangnam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.AddressUpdateHistoryDto;
import com.ptithcm.shopthoitrangnam.repository.AddressUpdateHistoryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressUpdateHistoryServiceImpl implements AddressUpdateHistoryService {
	@Autowired
	AddressUpdateHistoryRepository addressUpdateHistoryRepository;
	
	@Autowired
	WardService wardService;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public void insert(AddressUpdateHistoryDto addressUpdateHistoryDto) {
		String sql = "INSERT INTO CAPNHATDIACHI(ID_DIACHI, HOTEN, SDT, MAPHUONG_XA, DIACHICUTHE) VALUES (?, ?, ?, ?, ?)";
	    Query query = entityManager.createNativeQuery(sql);
	    query.setParameter(1, addressUpdateHistoryDto.getAddressId());
	    query.setParameter(2, addressUpdateHistoryDto.getFullName());
	    query.setParameter(3, addressUpdateHistoryDto.getPhoneNumber());
	    query.setParameter(4, addressUpdateHistoryDto.getWardCode());
	    query.setParameter(5, addressUpdateHistoryDto.getHomeNumber());
	    query.executeUpdate();
	}
}

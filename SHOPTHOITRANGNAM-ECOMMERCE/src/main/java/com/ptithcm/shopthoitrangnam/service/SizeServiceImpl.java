package com.ptithcm.shopthoitrangnam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.shopthoitrangnam.dto.SizeDto;
import com.ptithcm.shopthoitrangnam.entity.Size;
import com.ptithcm.shopthoitrangnam.repository.SizeRepository;

import jakarta.transaction.Transactional;

@Service
public class SizeServiceImpl implements SizeService {
	@Autowired
	SizeRepository sizeRepository;
	
	@Override
	public List<Size> findAll() {
		return sizeRepository.findAll();
	}
	
	@Override
	public Optional<Size> findBySizeCode(String sizeCode) {
		return sizeRepository.findBySizeCode(sizeCode);
	}
	
	@Override
	public List<Size> findBySizeCodeUsingRegex(String pattern) {
		return sizeRepository.findBySizeCodeUsingRegex(pattern);
	}
	
	@Override
	public List<Size> findBySizeNameUsingRegex(String pattern) {
		return sizeRepository.findBySizeNameUsingRegex(pattern);
	}
	
	@Override
	@Transactional
	public void deleteBySizeCode(String sizeCode) {
		sizeRepository.deleteBySizeCode(sizeCode);
	}
	
	@Override
	@Transactional
	public void save(SizeDto sizeDto) {
		Size size = new Size();
		size.setSizeCode(sizeDto.getSizeCode());
		size.setSizeName(sizeDto.getSizeName());
		sizeRepository.save(size);
	}
}

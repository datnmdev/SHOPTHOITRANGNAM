package com.ptithcm.shopthoitrangnam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void setValue(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	@Override
	public Object getValue(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	@Override
	public void deleteKey(String key) {
		redisTemplate.delete(key);
	}
}

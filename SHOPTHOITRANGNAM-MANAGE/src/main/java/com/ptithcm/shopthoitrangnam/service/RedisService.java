package com.ptithcm.shopthoitrangnam.service;

public interface RedisService {
	public void setValue(String key, Object value);
	
	public Object getValue(String key); 
	
	public void deleteKey(String key);
}

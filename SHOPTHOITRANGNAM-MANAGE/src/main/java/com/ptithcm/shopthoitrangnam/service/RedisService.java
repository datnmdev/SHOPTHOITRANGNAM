package com.ptithcm.shopthoitrangnam.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
	public void setValue(String key, Object value);
	
	public Object getValue(String key); 
	
	public void deleteKey(String key);
	
	public void setExpire(String key, long timeOut, TimeUnit timeUnit);
}

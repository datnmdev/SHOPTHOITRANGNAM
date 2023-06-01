package com.ptithcm.shopthoitrangnam.service;

import jakarta.mail.MessagingException;

public interface MailService {
	public void sendEmail(String to, String subject, String text);
	
	public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException;
}

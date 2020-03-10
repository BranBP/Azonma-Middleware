package com.azonma.service;

import com.azonma.exceptions.MailException;


public interface MailService {

	public void sendMail(String subject, String message, String... to)
			throws MailException;

}

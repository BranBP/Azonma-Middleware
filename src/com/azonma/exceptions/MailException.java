package com.azonma.exceptions;

public class MailException extends ServiceException {
	
	public MailException() {
		super();
	}
	
	public MailException(String message) {
		this(message,null);		
	}
	

	public MailException(Throwable cause) {
		this(null,cause);		
	}
	
	public MailException(String message, Throwable cause) {
		super(message,cause);		
	}

}

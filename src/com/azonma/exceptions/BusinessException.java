package com.azonma.exceptions;

@SuppressWarnings("serial")
public class BusinessException extends MyCompanyException { 
       

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		this(message,null);		
	}
	

	public BusinessException(Throwable cause) {
		this(null,cause);		
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message,cause);		
	}
    
}
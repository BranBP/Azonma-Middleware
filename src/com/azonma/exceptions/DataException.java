package com.azonma.exceptions;

@SuppressWarnings("serial")
public class DataException extends BusinessException {
       

	public DataException() {
		super();
	}
	
	public DataException(String message) {
		this(message,null);		
	}
	

	public DataException(Throwable cause) {
		this(null,cause);		
	}
	
	public DataException(String message, Throwable cause) {
		super(message,cause);		
	}
    
}
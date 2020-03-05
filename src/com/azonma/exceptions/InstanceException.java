package com.azonma.exceptions;

@SuppressWarnings("serial")
public abstract class InstanceException extends DataException {

	private Object key;
	private String className;

	protected InstanceException(String specificMessage, Object key, 
			String className) {

		super(specificMessage + " (key = '" + key + "' - className = '" + 
				className + "')");
		this.key = key;
		this.className = className;

	}

	public Object getKey() {
		return key;
	}

	public String getClassName() {
		return className;
	}

}
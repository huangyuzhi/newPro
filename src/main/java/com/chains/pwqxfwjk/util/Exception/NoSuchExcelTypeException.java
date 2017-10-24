package com.chains.pwqxfwjk.util.Exception;

public class NoSuchExcelTypeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchExcelTypeException() {
		super();
	}

	public NoSuchExcelTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoSuchExcelTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchExcelTypeException(String message) {
		super(message);
	}

	public NoSuchExcelTypeException(Throwable cause) {
		super(cause);
	}
	
	
}

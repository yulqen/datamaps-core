package com.matthewlemon.datamaps.core.exceptions;

public class ExcelParsedValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcelParsedValueException() {
		super();
	}

	public ExcelParsedValueException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcelParsedValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelParsedValueException(String message) {
		super(message);
	}

	public ExcelParsedValueException(Throwable cause) {
		super(cause);
	}
	
	

}

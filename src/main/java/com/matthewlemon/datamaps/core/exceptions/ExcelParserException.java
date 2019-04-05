package com.matthewlemon.datamaps.core.exceptions;

public class ExcelParserException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcelParserException() {
		super();
	}

	public ExcelParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcelParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelParserException(String message) {
		super(message);
	}

	public ExcelParserException(Throwable cause) {
		super(cause);
	}

	
}

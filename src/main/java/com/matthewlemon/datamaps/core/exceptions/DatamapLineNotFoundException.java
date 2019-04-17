package com.matthewlemon.datamaps.core.exceptions;

public class DatamapLineNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatamapLineNotFoundException() {
		super();
	}

	public DatamapLineNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatamapLineNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatamapLineNotFoundException(String message) {
		super(message);
	}

	public DatamapLineNotFoundException(Throwable cause) {
		super(cause);
	}
	

}

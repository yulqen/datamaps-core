package com.matthewlemon.datamaps.core.exceptions;

public class DatamapNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatamapNotFoundException() {
		super();
	}

	public DatamapNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatamapNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatamapNotFoundException(String message) {
		super(message);
	}

	public DatamapNotFoundException(Throwable cause) {
		super(cause);
	}

}

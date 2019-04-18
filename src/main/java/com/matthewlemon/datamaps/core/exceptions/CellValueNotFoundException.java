package com.matthewlemon.datamaps.core.exceptions;

public class CellValueNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CellValueNotFoundException() {
		super();
	}

	public CellValueNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CellValueNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CellValueNotFoundException(String message) {
		super(message);
	}

	public CellValueNotFoundException(Throwable cause) {
		super(cause);
	}

}

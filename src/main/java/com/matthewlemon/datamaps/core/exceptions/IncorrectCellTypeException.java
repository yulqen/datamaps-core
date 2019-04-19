package com.matthewlemon.datamaps.core.exceptions;

public class IncorrectCellTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public IncorrectCellTypeException() {
		super();
	}

	public IncorrectCellTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IncorrectCellTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCellTypeException(String message) {
		super(message);
	}

	public IncorrectCellTypeException(Throwable cause) {
		super(cause);
	}

}

package com.matthewlemon.datamaps.core.exceptions;

public class EmptyCellException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyCellException() {
		super();
	}

	public EmptyCellException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyCellException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyCellException(String message) {
		super(message);
	}

	public EmptyCellException(Throwable cause) {
		super(cause);
	}

	
}

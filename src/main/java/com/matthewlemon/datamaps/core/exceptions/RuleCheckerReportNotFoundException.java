package com.matthewlemon.datamaps.core.exceptions;

public class RuleCheckerReportNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuleCheckerReportNotFoundException() {
		super();
	}

	public RuleCheckerReportNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RuleCheckerReportNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuleCheckerReportNotFoundException(String message) {
		super(message);
	}

	public RuleCheckerReportNotFoundException(Throwable cause) {
		super(cause);
	}

}

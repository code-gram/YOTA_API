package com.yash.yotaapi.exception;

public class MarksNotFound extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarksNotFound() {
		super();
	}

	public MarksNotFound(String errorMsg) {
		super(errorMsg);
	}
}

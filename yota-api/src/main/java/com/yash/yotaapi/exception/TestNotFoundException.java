package com.yash.yotaapi.exception;

public class TestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public TestNotFoundException() {
	}

	public TestNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}

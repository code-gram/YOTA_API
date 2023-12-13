package com.yash.yotaapi.exception;

public class CompetencyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public CompetencyNotFoundException() {
	}

	public CompetencyNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}

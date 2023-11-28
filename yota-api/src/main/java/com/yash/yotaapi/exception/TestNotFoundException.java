package com.yash.yotaapi.exception;

public class TestNotFoundException extends RuntimeException {
	
	/**
	 * This is an unchecked exception. 
	 * Uses parameterized constructor to create customized error message. 
	 * Also uses default constructor to TestNotFoundException.
	 */
	
	private static final long serialVersionUID = 1L;

	public TestNotFoundException() {
	   super();
	}
	
	public TestNotFoundException(String errMsg) {
		   super(errMsg);
		}
}

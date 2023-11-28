package com.yash.yotaapi.exception;

/**
 * This is an unchecked exception.
 * Uses parameterized constructor to create
 * customized error message. Also uses default constructor to TestException.
 * 
 * @author dimpal.gaur
 *
 */
public class TestException extends RuntimeException {


	
	private static final long serialVersionUID = 1L;
	public TestException() {
		super();
	}
	public TestException(String errMsg) {
		super(errMsg);
	}
}

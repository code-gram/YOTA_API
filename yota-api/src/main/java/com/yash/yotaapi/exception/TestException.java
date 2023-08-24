package com.yash.yotaapi.exception;

/**
 * This is an unchecked exception. This will be creating the TestException Object for two purpose.
 * 1: To test test Exception at the time of TDD, this will be done by Default Constructor.
 * 2: To create the customized Exception message, this will be done by Parameterized Constructor.  
 * @author jaie.arkadi
 *
 */
public class TestException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TestException() {
		super();
	}
	public TestException(String errormsg) {
		super(errormsg);
	}

}

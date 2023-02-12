package com.yash.yotaapi.exception;

/**
 * This is an unchecked exception.
 * Uses parameterized constructor to create customized error message.
 * Also uses default constructor to test associate exception.
 * @author nitin.chougale
 *
 */
public class AssociateDetailsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public AssociateDetailsException(String errorMsg) {
		super(errorMsg);
	}
	
	public AssociateDetailsException() {
		super();
	}
}

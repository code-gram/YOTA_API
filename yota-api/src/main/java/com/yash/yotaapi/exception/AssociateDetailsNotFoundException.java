package com.yash.yotaapi.exception;

/**
 * This is an unchecked exception.
 * Uses parameterized constructor to create customized error message.
 * Also uses default constructor to test associate not found exception.
 * @author nitin.chougale
 */
public class AssociateDetailsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public AssociateDetailsNotFoundException() {
		super();
	}

	public AssociateDetailsNotFoundException(String error) {
		super (error);
	}
}

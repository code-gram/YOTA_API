package com.yash.yotaapi.exception;
/**
 * This is an unchecked exception. This will be creating the TechnologyException Object for two purpose. 
 * 1: To test Technology Exception at the time of TDD, this will be done by Default Constructor. 
 * 2: To create the customized Exception message, this will be done by Parameterized Constructor.  
 * @author pankaj.ssharma
 *
 */
public class ParentTechnologyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParentTechnologyException() {
		super();
	}
	
	public ParentTechnologyException(String errorMsg) {
		super(errorMsg);
	}
}

package com.yash.yotaapi.exceptions;

public class TechnologyAlreadyAvailableException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TechnologyAlreadyAvailableException(String message){
		super(message);
	}
	
}

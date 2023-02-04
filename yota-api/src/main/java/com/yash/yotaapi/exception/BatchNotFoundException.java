package com.yash.yotaapi.exception;

/*BatchNotFoundException class used to handle Runtimeexception.*/
public class BatchNotFoundException extends RuntimeException {
	
	public BatchNotFoundException(String msg) {
		super(msg);
		
	}

}

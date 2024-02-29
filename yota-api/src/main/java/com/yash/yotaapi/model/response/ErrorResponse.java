package com.yash.yotaapi.model.response;

public class ErrorResponse {
	 private String message;

	    public ErrorResponse(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public static ErrorResponse builder() {
	        return new ErrorResponse(null);
	    }

	    public ErrorResponse message(String message) {
	        this.message = message;
	        return this;
	    }

	    @Override
	    public String toString() {
	        return "ErrorResponse{" +
	                "message='" + message + '\'' +
	                '}';
	    }


}

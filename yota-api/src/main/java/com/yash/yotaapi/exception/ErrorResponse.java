package com.yash.yotaapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Used To Store The Exception Error Message From Exception Handler
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	int status;
	HttpStatus error;
	String msg;
	Date date;
	String path;

}

package com.yash.yotaapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*ErrorResponse class is used to customize error message.
 * @author anil.shimpi
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	String msg;
	

}

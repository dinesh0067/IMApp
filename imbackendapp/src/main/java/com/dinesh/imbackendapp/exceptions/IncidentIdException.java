package com.dinesh.imbackendapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncidentIdException extends RuntimeException {

	public IncidentIdException(String message) {
		super(message);
	}
	
	
	
	

}

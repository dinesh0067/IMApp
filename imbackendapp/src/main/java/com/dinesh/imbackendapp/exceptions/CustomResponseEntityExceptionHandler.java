package com.dinesh.imbackendapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleIncidentIdException(IncidentIdException ex, WebRequest request){
		IncidentIdExceptionResponse exceptionResponse = new IncidentIdExceptionResponse(ex.getMessage());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	    public final ResponseEntity<Object> handleIncidentNotFoundException(IncidentNotFoundException ex, WebRequest request){
	        IncidentNotFoundExceptionResponse exceptionResponse = new IncidentNotFoundExceptionResponse(ex.getMessage());
	        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	    }
	
	 @ExceptionHandler
	    public final ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex, WebRequest request){
	        UsernameAlreadyExistsResponse exceptionResponse = new UsernameAlreadyExistsResponse(ex.getMessage());
	        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	    }

}

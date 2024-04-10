package com.varsha.Employeebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException validataionException) {

		return new ResponseEntity<>(validataionException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(value = Exception.class)
//	public ResponseEntity<?> exception(Exception e) {
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
//	}
}

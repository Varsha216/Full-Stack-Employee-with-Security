package com.varsha.identityprovider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerAdvice {


	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<?> handleValidationException(AdminNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
	}
}

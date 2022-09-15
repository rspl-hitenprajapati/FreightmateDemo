package com.freightmate.connote.advice;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ConnoteAdvice {

	private static final Logger log = LogManager.getLogger(ConnoteAdvice.class);
	
	@InitBinder
	private void activateDirectFieldAccess(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(RuntimeException e, HttpServletRequest request) {
		log.error("IllegalArgumentException error occurred.", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(RuntimeException e, HttpServletRequest request) {
		log.error("Unexpected error occurred.", e);
		return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("INTERNAL_SERVER_ERROR"));
	}

}
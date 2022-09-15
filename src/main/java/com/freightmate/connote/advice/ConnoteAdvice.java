package com.freightmate.connote.advice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author hiten.prajapati
 * @implNote ControllerAdvice for handling exceptions and resolve with custom messages.
 */
@ControllerAdvice
public class ConnoteAdvice extends ResponseEntityExceptionHandler{

	private static final Logger log = LogManager.getLogger(ConnoteAdvice.class);
	
	@InitBinder
	private void activateDirectFieldAccess(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("One or more validations failed : {}", e.getMessage());
		List<String> springValidationErrors = new ArrayList<>();

		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		for (ObjectError objectError : errors) {
			springValidationErrors.add(objectError.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(String.join(", ", springValidationErrors)));
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
package com.cwp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cwp.Exceptions.EmailAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex){

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error ->

			errors.put(error.getField(), error.getDefaultMessage())

		);

		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler({ EmailAlreadyExistsException.class })
	public ResponseEntity<?> handleEmailConflict(EmailAlreadyExistsException ex) {
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

}

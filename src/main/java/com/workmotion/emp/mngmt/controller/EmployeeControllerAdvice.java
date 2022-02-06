package com.workmotion.emp.mngmt.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.workmotion.emp.mngmt.model.ErrorMessage;

@ControllerAdvice
public class EmployeeControllerAdvice {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handlePersistenceException(ConstraintViolationException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	}

}

package com.challenge.idincrement.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.challenge.idincrement.exception.InvalidRequestException;
import com.challenge.idincrement.exception.UnauthorizedException;
import com.challenge.idincrement.model.ExceptionReturnMessage;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ExceptionReturnMessage> handleInvalidRequestException(HttpServletRequest request, Exception e) {
		logger.error("{}", e.getMessage(), e);
		ExceptionReturnMessage exceptionReturnMessage = new ExceptionReturnMessage(HttpStatus.BAD_REQUEST, e, e.getMessage(), request);
		return new ResponseEntity<>(exceptionReturnMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ExceptionReturnMessage> handleUnauthorizedException(HttpServletRequest request, Exception e) {
		logger.error("{}", e.getMessage(), e);
		ExceptionReturnMessage exceptionReturnMessage = new ExceptionReturnMessage(HttpStatus.UNAUTHORIZED, e, e.getMessage(), request);
		return new ResponseEntity<>(exceptionReturnMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionReturnMessage> handleHttpMessageNotReadableException(HttpServletRequest request, Exception e) {
		logger.error("There are issues with the informed request structure, {}", e.getMessage(), e);
		ExceptionReturnMessage exceptionReturnMessage = new ExceptionReturnMessage(HttpStatus.BAD_REQUEST, e, e.getMessage(), request);
		return new ResponseEntity<>(exceptionReturnMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionReturnMessage> handleAnyOtherException(HttpServletRequest request, Exception e) {
		logger.error("{}", e.getMessage(), e);
		ExceptionReturnMessage exceptionReturnMessage = new ExceptionReturnMessage(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage(), request);
		return new ResponseEntity<>(exceptionReturnMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

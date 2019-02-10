package com.challenge.idincrement.exception;

public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message) {
		super("The request is invalid: " + message);
	}
}

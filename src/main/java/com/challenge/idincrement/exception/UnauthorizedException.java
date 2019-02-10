package com.challenge.idincrement.exception;

public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
		super("User unauthorized: " + message);
	}
}

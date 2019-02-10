package com.challenge.idincrement.model;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class ExceptionReturnMessage {

	private long timestamp;
	private int status;
	private String error;
	private String exception;
	private String message;
	private String path;

	public ExceptionReturnMessage(HttpStatus status, Exception exception, String message, HttpServletRequest request) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timestamp = timestamp.getTime();
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.exception = exception.getClass().getSimpleName();
		this.message = message;
		this.path = request.getServletPath();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}

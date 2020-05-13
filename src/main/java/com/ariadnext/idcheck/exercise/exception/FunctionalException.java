package com.ariadnext.idcheck.exercise.exception;

public class FunctionalException extends RuntimeException{
	
	private final String errorMessage;
	
	public FunctionalException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

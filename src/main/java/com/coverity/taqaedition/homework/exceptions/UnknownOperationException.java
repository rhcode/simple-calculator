package com.coverity.taqaedition.homework.exceptions;

/*
 * Custom Exception to provide information about operations not
 * supported by the calculator
 */
public class UnknownOperationException extends Exception {
	private static final long serialVersionUID = 6126690489984356487L;
	
	private static String description = "An unknown operation has been provided : ";
	
	public UnknownOperationException(String message) {
		super(description + message);
	}
}

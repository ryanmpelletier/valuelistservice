package com.pelletier.valuelist.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author Ryan Pelletier
 *
 *	Wraps an Exception with a message, and field name. 
 *	Useful for serialized JSON response in front-end.
 */
public class ErrorInfo {
	/**
	 * A key for this exception. Usually the name of an invalid field.
	 */
	private String key;
	
	/**
	 * A user-friendly message explaining the reason for the exception.
	 */
	private String simpleMessage;
	/**
	 * The actual exception.
	 */
	private Exception exception;
	
	public ErrorInfo(){}
	
	public ErrorInfo(String key,String simpleMessage, Exception exception){
		this.key = key;
		this.simpleMessage = simpleMessage;
		this.exception = exception;
	}

	public String getKey() {
		return key;
	}


	public String getSimpleMessage() {
		return simpleMessage;
	}
	
	public String getExceptionMessage(){
		return exception.getMessage();
	}
	
	@JsonIgnore
	public Exception getException() {
		return exception;
	}

}

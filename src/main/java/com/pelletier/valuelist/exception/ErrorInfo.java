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
	 * The field which this error applies to
	 */
	private String field;
	
	/**
	 * A user-friendly message explaining the reason for the exception.
	 */
	private String simpleMessage;
	/**
	 * The actual exception which is wrapped.
	 */
	private Exception exception;
	
	public ErrorInfo(){}
	
	public ErrorInfo(String field,String simpleMessage, Exception exception){
		this.field = field;
		this.simpleMessage = simpleMessage;
		this.exception = exception;
	}

	public String getField() {
		return field;
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

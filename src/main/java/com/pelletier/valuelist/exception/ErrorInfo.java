package com.pelletier.valuelist.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorInfo {
	private String field;
	private String simpleMessage;
	private RuntimeException exception;
	
	public ErrorInfo(){}
	
	public ErrorInfo(String field,String simpleMessage, RuntimeException exception){
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
	public RuntimeException getException() {
		return exception;
	}

}

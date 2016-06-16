package com.pelletier.valuelist.exception;

public class ErrorInfo {
	private int code;
	private String field;
	private RuntimeException exception;
	
	public ErrorInfo(){}
	
	public ErrorInfo(int code, String field, RuntimeException exception){
		this.code = code;
		this.field = field;
		this.exception = exception;
	}

	public int getCode() {
		return code;
	}

	public String getField() {
		return field;
	}

	public String getException() {
		return exception.getMessage();
	}
	
	
}

package com.pelletier.valuelist;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author Ryan Pelletier
 * 
 * Used to wrap multiple exceptions, helpful for describing parameter conversion errors.
 *
 */
public class ConversionException extends RuntimeException {
	
	private static final long serialVersionUID = -194977504061004798L;
	
	/**
	 * List of ErrorInfo objects, which wrap a RuntimeException, field name, and message,
	 */
	private List<ErrorInfo> errorInfos;
	
	public void addError(String key,String message, Exception exception)
	{
		addErrorInfo(new ErrorInfo(key, message, exception));
	}
	/**
	 * @param errorInfo
	 * 
	 * the errorInfo to add to the exceptions list of errorInfos
	 */
	public void addErrorInfo(ErrorInfo errorInfo){
		if(this.errorInfos == null){
			errorInfos = new ArrayList<>();
		}
		errorInfos.add(errorInfo);
	}

	public List<ErrorInfo> getErrorInfos() {
		return errorInfos;
	}
	
	public static class ErrorInfo {
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
}

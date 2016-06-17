package com.pelletier.valuelist.exception;

import java.util.ArrayList;
import java.util.List;
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
	
	/**
	 * @param errorInfo
	 * 
	 * the errorInfo to add to the exceptions list of errorInfos
	 */
	public synchronized void addErrorInfo(ErrorInfo errorInfo){
		if(this.errorInfos == null){
			errorInfos = new ArrayList<>();
		}
		errorInfos.add(errorInfo);
	}

	public List<ErrorInfo> getErrorInfos() {
		return errorInfos;
	}
}

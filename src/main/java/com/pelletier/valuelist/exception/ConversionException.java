package com.pelletier.valuelist.exception;

import java.util.ArrayList;
import java.util.List;

public class ConversionException extends RuntimeException {
	
	/**
	 * I don't really know what this is doing.
	 */
	private static final long serialVersionUID = 1L;

	public final static int INVALID = 0;
	
	private List<ErrorInfo> errorInfos;
	
	public void addErrorInfo(ErrorInfo errorInfo){
		if(this.errorInfos == null){
			errorInfos = new ArrayList<>();
		}
		errorInfos.add(errorInfo);
	}

	public List<ErrorInfo> getErrorInfos() {
		return errorInfos;
	}
	
	
	
	
	
}

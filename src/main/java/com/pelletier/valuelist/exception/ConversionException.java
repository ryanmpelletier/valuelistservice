package com.pelletier.valuelist.exception;

import java.util.ArrayList;
import java.util.List;

public class ConversionException extends RuntimeException {
	
	private static final long serialVersionUID = -194977504061004798L;
	
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

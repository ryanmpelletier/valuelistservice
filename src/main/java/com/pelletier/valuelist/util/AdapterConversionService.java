package com.pelletier.valuelist.util;

public interface AdapterConversionService {
	/**
	 * 
	 * @param paramKey
	 * 
	 * Name of parameter that must be converted.
	 * 
	 * @param paramValue
	 * 
	 * Value of parameter that must be converted.
	 * 
	 * @return
	 * 
	 * paramValue converted to target type
	 */
    public <T> T convertIfNeeded(String paramKey, Object paramValue);
}

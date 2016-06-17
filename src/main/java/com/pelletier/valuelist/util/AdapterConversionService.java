package com.pelletier.valuelist.util;

import java.util.Map;

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
    public <T> Map<String,T> convert(Map<String,Object> params);
}

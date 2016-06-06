package com.pelletier.valuelist;

import java.util.Map;

import com.pelletier.valuelist.exception.AdapterNotFoundException;

/*
 *Client facing class 
 * 
 */
public interface ValueListService {

	public Values<? extends Object> getValuesList(String key, Map<String,Object> queryParams, ValuesInfo valuesInfo) throws AdapterNotFoundException;
	
}

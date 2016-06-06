package com.pelletier.valuelist;

import java.util.Map;

import com.pelletier.valuelist.exception.AdapterNotFoundException;

/*
 *Client facing class 
 * 
 */
public interface ValueListService {

	//since my ValueListService implementation might not use an Adapter, it seems odd to have it say
	//that it throws that specific type of exception
	public Values<? extends Object> getValuesList(String key, Map<String,Object> queryParams, ValuesInfo valuesInfo) throws AdapterNotFoundException;
	
}

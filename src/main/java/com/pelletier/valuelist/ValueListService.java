package com.pelletier.valuelist;

import java.util.Map;

/**
 * 
 * @author Ryan Pelletier
 * 
 * This is the client facing interface.
 *
 */
public interface ValueListService {
	public Values<? extends Object> getValuesList(String key, Map<String,Object> queryParams, PagingInfo valuesInfo) throws RuntimeException;	
}

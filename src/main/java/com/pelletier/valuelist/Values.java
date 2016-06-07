package com.pelletier.valuelist;

import java.util.List;

public interface Values<T> {
	
	/*
	 * this is a list of T objects, these are the actual data of the request
	 * so, for example, a sql request would likely return List<Map<String,Object>>
	 */
	
	List<T> getValues();
	
	/*
	 * This is information about the request, such as paging, total items info, etc.
	 */
	PagingInfo getValuesInfo();
}

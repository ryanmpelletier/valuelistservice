package com.pelletier.valuelist;

import java.util.List;
/**
 * 
 * @author Ryan Pelletier
 *
 * @param <T>
 * The type of the "Transfer Object" which the client is concerned about
 */
public interface Values<T> {
	
	/*
	 * this is a list of T objects, these are the actual data of the request
	 * so, for example, a SQL request would likely return List<Map<String,Object>>
	 */
	
	List<T> getValues();
	
	/*
	 * This is information about the request, such as paging, total items info, etc.
	 */
	PagingInfo getValuesInfo();
}

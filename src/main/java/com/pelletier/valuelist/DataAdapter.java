package com.pelletier.valuelist;

import java.util.Map;
/**
 * 
 * 
 * The intention of DataAdapter is to allow one ValueListService to be able to service
 * from multiple types of data sources, or multiple queries from one data source.
 * @author Ryan Pelletier
 *
 * @param <T>
 *
 * T is the "Transfer Object", the type of object which the client cares about.
 * 
 *
 */
public interface DataAdapter<T> {
	
	/**
	 * 
	 * @param params
	 * Query parameters.
	 * 
	 * @param pagingInfo
	 * Requested paging.
	 * 
	 * @return
	 * Return Values, which wraps List<T> and PagingInfo
	 */
	public Values<T> query(Map<String,Object> params, PagingInfo pagingInfo);
}

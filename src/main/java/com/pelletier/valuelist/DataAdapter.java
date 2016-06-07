package com.pelletier.valuelist;

import java.util.Map;
/**
 * 
 * @author Ryan Pelletier
 *
 * @param <T>
 *
 * T is the "Transfer Object", the type of object which the client cares about.
 * 
 * The intention of DataAdapter is to allow one ValueListService to be able to service
 * from multiple types of data sources, or multiple queries from one data source.
 *
 */
public interface DataAdapter<T> {
	public Values<T> query(Map<String,Object> params, PagingInfo valuesInfo);
}

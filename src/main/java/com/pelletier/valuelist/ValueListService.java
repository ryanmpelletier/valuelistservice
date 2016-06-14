package com.pelletier.valuelist;

import java.util.Map;

/**
 * This is the client facing interface.
 * Returns a Values<? extends Object> which is essentially
 * a List<? extends Object> and PagingInfo.
 * 
 * @author Ryan Pelletier
 *
 */
public interface ValueListService {
	/**
	 * 
	 * @param key
	 * The key of a query to run.
	 * 
	 * @param queryParams
	 * Parameters to run the query with.
	 * 
	 * @param pagingInfo
	 * Requested paging information.
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public Values<? extends Object> getValuesList(String key, Map<String,Object> queryParams, PagingInfo pagingInfo) throws RuntimeException;	
}

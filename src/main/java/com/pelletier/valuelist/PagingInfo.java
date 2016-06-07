package com.pelletier.valuelist;

/**
 * 
 * @author Ryan Pelletier
 * 
 * Provides paging information for requests and responses.
 *
 */
public interface PagingInfo {
	
	public int getTotalCount();
	public int getPage();
	public int getNumberPerPage();
		
}

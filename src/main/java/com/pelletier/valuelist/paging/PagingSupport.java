package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;

/**
 * 
 * @author Ryan Pelletier
 * 
 * Provides information necessary to support paging.
 *
 */

public interface PagingSupport {
	public String getCountQuery(String query);
	public String getPagedQuery(String query, PagingInfo pagingInfo);
}

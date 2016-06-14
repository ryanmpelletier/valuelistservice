package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;

/**
 * 
 * Provides pagination support for Oracle Databases
 * 
 * Pre and post SQL can be injected, note that post SQL must have place-holders
 * pageNumber and numberPerPage
 * 
 * Note: In Oracle ROWNUM is 1 based, this hasn't been tested.
 * 
 * 
 * @author Ryan Pelletier
 *
 */


public class OraclePagingSupport implements PagingSupport
{

	/**
	 * Wraps user provided query in count query.
	 */
	@Override
	public String getCountQuery(String query)
	{
		return "SELECT count(*) FROM (" + query + ")";
	}

	/**
	 * Wraps user provided query in Oracle paging query with user provided paging information.
	 */
	@Override
	public String getPagedQuery(String query, PagingInfo pagingInfo)
	{
		return "SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (" + query + ") INNER ) WRAPPED "
				+ "WHERE WRAPPED.RECORDNUM BETWEEN "
				+ (((pagingInfo.getPage() - 1) * pagingInfo.getNumberPerPage()) + 1) 
				+ " AND "
				+ (pagingInfo.getPage()) * pagingInfo.getNumberPerPage();
	}

}
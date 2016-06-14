package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;

/**
 * 
 * 	Provides pagination support for Oracle Databases
 * 
 *  Pre and post SQL can be injected, note that post SQL must have place-holders pageNumber and numberPerPage
 * 
 *  Note: This hasn't been tested.
 * 
 * 
 * @author Ryan Pelletier
 *
 */



		
	public class OraclePagingSupport implements PagingSupport
	{

	@Override
	public String getCountQuery(String query)
	{
		return "SELECT count(*) FROM (" + query + ")";
	}

	@Override
	public String getPagedQuery(String query, PagingInfo pagingInfo)
	{
		return "SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (" + query + ") INNER ) WRAPPED " + 
				"WHERE WRAPPED.RECORDNUM BETWEEN "+
				(((pagingInfo.getPage() - 1) * pagingInfo.getNumberPerPage()) + 1)  + 
				" AND " + 
				(pagingInfo.getPage()) * pagingInfo.getNumberPerPage();
	}

}



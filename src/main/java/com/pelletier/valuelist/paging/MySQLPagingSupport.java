package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;

/**
 * 	Provides pagination support for MySQL databases.
 * @author Ryan Pelletier
 */

public class MySQLPagingSupport implements PagingSupport {
	
	/**
	 * Wraps user provided query in MySQL count query
	 */
	@Override
	public String getCountQuery(String sql) {
		return "SELECT count(*) as totalCount FROM (" + sql + ") AS Alias";
	}

	/**
	 * Appends LIMIT clause to user provided query, with user provided paging information.
	 */
	@Override
	public String getPagedQuery(String sql, PagingInfo pagingInfo) {
		return sql + " LIMIT " + (pagingInfo.getPage() - 1) * pagingInfo.getNumberPerPage() + ", " + pagingInfo.getNumberPerPage(); 
	}

}

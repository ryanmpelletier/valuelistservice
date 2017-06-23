package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;

/**
 * Provides pagination support for PostgreSQL databases.
 * @author M.L.Wilson
 *
 */
public class DerbyPagingSupport implements PagingSupport {
	
	/**
	 * Wraps user provided query in PostgreSQL count query
	 */
	@Override
	public String getCountQuery(String sql) {
		sql = String.format("SELECT count(*) FROM (%s) AS i", sql);
		return sql;
	}

	/**
	 * Appends LIMIT and OFFSET clause to user provided query, with user provided paging information.
	 */
	@Override
	public String getPagedQuery(String sql, PagingInfo pagingInfo) {
		
		int limit = Math.max(1, pagingInfo.getNumberPerPage());
		int offset = Math.max(0, (pagingInfo.getPage()-1)) * limit;
		                        
		sql = String.format("%s OFFSET %d ROWS FETCH FIRST %d ROWS ONLY", sql, offset, limit);
		
		return sql;
	}

}

package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;


public class MySQLPagingSupport implements PagingSupport {
	
	@Override
	public String getCountQuery(String sql) {
		return "SELECT count(*) as totalCount FROM (" + sql + ") AS Alias";
	}

	@Override
	public String getPagedQuery(String sql, PagingInfo pagingInfo) {
		return sql + " LIMIT " + (pagingInfo.getPage() - 1) * pagingInfo.getNumberPerPage() + ", " + pagingInfo.getNumberPerPage(); 
	}

}

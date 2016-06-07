package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;

public interface PagingSupport {
	public String getCountQuery(String query);
	public String getPagedQuery(String query, PagingInfo pagingInfo);
}

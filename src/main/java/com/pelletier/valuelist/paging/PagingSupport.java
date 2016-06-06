package com.pelletier.valuelist.paging;

public interface PagingSupport {
	public String getCountQuery(String query);
	public String getPagedQuery(String query);
}

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


public class OraclePagingSupport implements PagingSupport {
	
	private String pagedQueryPreSql = "SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (";
	private String pagedQueryPostSql = ") INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN (([pageNumber]-1)*[numberPerPage]+1) AND (([pageNumber]-1)*[numberPerPage]+[numberPerPage])";

	@Override
	public String getCountQuery(String query) {
		return "SELECT count(*) FROM (" + query + ")";
	}

	@Override
	public String getPagedQuery(String query, PagingInfo pagingInfo) {
    	return pagedQueryPreSql + query + pagedQueryPostSql.replace("pageNumber",new Integer(pagingInfo.getPage()).toString()).replace("numberPerPage",new Integer(pagingInfo.getNumberPerPage()).toString());
	}

	public void setPagedQueryPreSql(String pagedQueryPreSql) {
		this.pagedQueryPreSql = pagedQueryPreSql;
	}

	public void setPagedQueryPostSql(String pagedQueryPostSql) {
		this.pagedQueryPostSql = pagedQueryPostSql;
	}

	

}

package com.pelletier.valuelist.paging;

import com.pelletier.valuelist.PagingInfo;

/**
 * 
 * 	Provides pagination support for Oracle Databases
 * 
 * @author Ryan Pelletier
 *
 */


public class OraclePagingSupport implements PagingSupport {
	
	private String pagedQueryPreSql = "SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (";
	private String pagedQueryPostSql = ") INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN (([pagingPage]-1)*[pagingNumberPer]+1) AND (([pagingPage]-1)*[pagingNumberPer]+[pagingNumberPer])";

	@Override
	public String getCountQuery(String query) {
		return "SELECT count(*) FROM (" + query + ")";
	}

	@Override
	public String getPagedQuery(String query, PagingInfo pagingInfo) {
		return pagedQueryPreSql + query + pagedQueryPostSql;
	}

	public void setPagedQueryPreSql(String pagedQueryPreSql) {
		this.pagedQueryPreSql = pagedQueryPreSql;
	}

	public void setPagedQueryPostSql(String pagedQueryPostSql) {
		this.pagedQueryPostSql = pagedQueryPostSql;
	}

	

}

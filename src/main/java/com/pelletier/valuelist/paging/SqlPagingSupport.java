package com.pelletier.valuelist.paging;

/*
 * This class really just gets us the queries we need in case the
 * client wants to do paging. If they want to do paging I think we will need both queries.
 */

public class SqlPagingSupport implements PagingSupport {
	
	private String countQueryPreSql;
	private String countQueryPostSql;
	
    private String pagedQueryPreSql;
    private String pagedQueryPostSql;
	
	public String getCountQuery(String sql){
		return countQueryPreSql + sql + countQueryPostSql;
	}

	@Override
	public String getPagedQuery(String sql) {
		return pagedQueryPreSql + sql + pagedQueryPostSql;
	}

	public void setCountQueryPreSql(String countQueryPreSql) {
		this.countQueryPreSql = countQueryPreSql;
	}

	public void setCountQueryPostSql(String countQueryPostSql) {
		this.countQueryPostSql = countQueryPostSql;
	}

	public void setPagedQueryPreSql(String pagedQueryPreSql) {
		this.pagedQueryPreSql = pagedQueryPreSql;
	}

	public void setPagedQueryPostSql(String pagedQueryPostSql) {
		this.pagedQueryPostSql = pagedQueryPostSql;
	}
	
	

}

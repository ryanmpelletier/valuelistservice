package com.pelletier.valuelist;

public class DefaultPagingInfo implements PagingInfo {
	
	private int totalCount;
	private int page = 0;
	private int numberPerPage = 10;
	
	public DefaultPagingInfo(){}
	
	public DefaultPagingInfo(int page, int numberPerPage) {
		this.page = page;
		this.numberPerPage = numberPerPage;
	}

	@Override
	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public int getNumberPerPage() {
		return numberPerPage;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}
	
	
	
}

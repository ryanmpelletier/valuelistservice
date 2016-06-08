package com.pelletier.valuelist;


/**
 * 
 * @author Ryan Pelletier
 * 
 * The Default PagingInfo implementation. 
 * This simply stores total results, page, and number of results per page.
 * This type of object is used both in the request and response (if paging is supported)
 */
public class PagingInfo {
	
	private int totalCount;
	private int page = 0;
	private int numberPerPage = 10;
	
	public PagingInfo(){}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumberPerPage() {
		return numberPerPage;
	}

	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}
	
	
	
	
}

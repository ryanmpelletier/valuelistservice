package com.pelletier.valuelist;

/*
 * Im not really sure what is getting injected here and what isnt
 */

public class DefaultValuesInfo implements ValuesInfo {
	
	private int totalCount;
	private int page = 0;
	private int numberPerPage = 25;
	
	public DefaultValuesInfo(){}
	
	public DefaultValuesInfo(int totalCount, int page, int numberPerPage) {
		this.totalCount = totalCount;
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
}

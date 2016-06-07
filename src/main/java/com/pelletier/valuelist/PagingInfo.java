package com.pelletier.valuelist;
/*
 * 
 * 
 * I really don't like this. I think it should be a concrete object. 
 * I don't see the flexibility it provides to make this an interface. 
 * Also, I do not think it should be included in the method call separate from the query parameters. 
 * 
 * 
 * I find myself wanting to add methods to this, for setting. This leads me to think
 * the best way to implement this is just as an object with the appropriate fields. 
 * I think it would add too much complexity if its flexibility were taken advantage of.
 * 
 */
public interface PagingInfo {
	
	public int getTotalCount();
	public int getPage();
	public int getNumberPerPage();
		
}

package com.pelletier.valuelist;

import java.util.List;

/**
 * 
 * @author Ryan Pelletier
 *
 * @param <T>
 * The type of Java Object which will be returned to the client. 
 */
public class DefaultValues<T> implements Values<T>{
	
	
	List<T> values;
	PagingInfo pagingInfo;
	
	public DefaultValues(List<T> values, PagingInfo pagingInfo) {
		this.values = values;
		this.pagingInfo = pagingInfo;
	}

	@Override
	public List<T> getValues() {
		return values;
	}

	@Override
	public PagingInfo getValuesInfo() {
		return pagingInfo;
	}
	
	/*
	 *To simplify debugging.
	 *Note: Does not do null checks. 
	 */
	@Override
	public String toString(){
		return values.toString() + 
				"\n Page Number: " + pagingInfo.getPage() +
				"\n Items Per Page: " + pagingInfo.getNumberPerPage() + 
				"\n Total Count: " + pagingInfo.getTotalCount();
	}

}

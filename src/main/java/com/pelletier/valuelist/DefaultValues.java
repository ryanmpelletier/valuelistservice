package com.pelletier.valuelist;

import java.util.List;

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
	
	//to simplify debugging override toString
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(values.toString());
		
		if( pagingInfo != null)
		{
			builder.append("\n Page Number: ").append(pagingInfo.getPage());
			builder.append("\n Items Per Page: ").append(pagingInfo.getNumberPerPage());
			builder.append("\n Total Count: ").append(pagingInfo.getTotalCount());
		}
		
		return builder.toString();

	}

}

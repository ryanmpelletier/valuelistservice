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
		return values.toString() + 
				"\n Page Number: " + pagingInfo.getPage() +
				"\n Items Per Page: " + pagingInfo.getNumberPerPage() + 
				"\n Total Count: " + pagingInfo.getTotalCount();
	}

}

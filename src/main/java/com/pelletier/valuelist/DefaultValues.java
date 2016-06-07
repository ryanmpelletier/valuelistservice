package com.pelletier.valuelist;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author Ryan Pelletier
 * 
 * Default Values implementation. This implementation is especially useful for
 * SQL DataAdapters, since rows can be represented easily with List<Map<String,Object>>
 * 
 * This object holds values of type List<Map<String,Object>>,
 * and a PagingInfo object.
 *
 */
public class DefaultValues implements Values<Map<String,Object>> {
	
	List<Map<String, Object>> values;
	PagingInfo valuesInfo;
	
	public DefaultValues(List<Map<String, Object>> values, PagingInfo valuesInfo) {
		this.values = values;
		this.valuesInfo = valuesInfo;
	}

	@Override
	public List<Map<String, Object>> getValues() {
		return values;
	}

	@Override
	public PagingInfo getValuesInfo() {
		return valuesInfo;
	}
	
	
	//to simplify debugging override toString
	@Override
	public String toString(){
		return values.toString() + 
				"\n Page Number: " + valuesInfo.getPage() +
				"\n Items Per Page: " + valuesInfo.getNumberPerPage() + 
				"\n Total Count: " + valuesInfo.getTotalCount();
	}

}

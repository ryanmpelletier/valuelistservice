package com.pelletier.valuelist;

import java.util.List;
import java.util.Map;

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

}

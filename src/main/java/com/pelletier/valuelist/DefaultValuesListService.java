package com.pelletier.valuelist;

import java.util.Map;

import com.pelletier.valuelist.exception.AdapterNotFoundException;

public class DefaultValuesListService<T> implements ValueListService {
	
	private Map<String, DataAdapter<T>> adapters;

	@Override
	public Values<T> getValuesList(String key, Map<String, Object> queryParams, ValuesInfo valuesInfo) throws AdapterNotFoundException {
		if(adapters.get(key) == null){
			throw new AdapterNotFoundException(key);
		}
		return adapters.get(key).query(queryParams, valuesInfo);
	}

	public void setAdapters(Map<String, DataAdapter<T>> adapters) {
		this.adapters = adapters;
	}
}

package com.pelletier.valuelist;

import java.util.Map;

public class DefaultValuesListService<T> implements ValueListService {
	
	private Map<String, DataAdapter<T>> adapters;

	@Override
	public Values<T> getValuesList(String key, Map<String, Object> queryParams, PagingInfo pagingInfo) throws RuntimeException {
		if(adapters.get(key) == null){
			throw new RuntimeException("Query could not be found for " + key + ", please check the Spring configuration.");
		}
		return adapters.get(key).query(queryParams, pagingInfo);
	}

	public void setAdapters(Map<String, DataAdapter<T>> adapters) {
		this.adapters = adapters;
	}
}

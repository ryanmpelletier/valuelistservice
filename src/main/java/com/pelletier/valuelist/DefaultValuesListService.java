package com.pelletier.valuelist;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
/**
 * 
 * Configurable map of DataAdapters allows for flexible data sources and queries.
 * 
 * @author Ryan Pelletier
 *
 * @param <T>
 * The type of the transfer object which will be returned to the client, 
 * as well as (potentially) paging information.
 */
public class DefaultValuesListService<T> implements ValueListService {
	
	/**
	 * Can configure DataAdapters in map, allows different queries through one ValueListService. 
	 */
	private Map<String, DataAdapter<T>> adapters;

	@Override
	public Values<T> getValuesList(String adapterKey, Map<String, Object> queryParams, PagingInfo pagingInfo) throws RuntimeException {
		
		if(adapters.get(adapterKey) == null){
			throw new RuntimeException("Query could not be found for " + adapterKey + ", please check the Spring configuration.");
		}

		return adapters.get(adapterKey).query(queryParams, pagingInfo);
	}

	@Autowired
	public void setAdapters(Map<String, DataAdapter<T>> adapters) {
		this.adapters = adapters;
	}
}

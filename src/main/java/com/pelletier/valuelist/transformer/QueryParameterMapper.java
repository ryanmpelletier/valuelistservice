package com.pelletier.valuelist.transformer;

import java.util.Map;

/**
 * 
 * Provides support to "inject" query parameters into a SQL string. The intention of this is not
 * to take Spring's place injecting parameters, however it could. Rather, the intention is to include
 * certain SQL if a parameter on the clients request exists. 
 * 
 * @author Ryan Pelletier
 * 
 *
 */
public interface QueryParameterMapper {
	public String transform(String query, Map<String, Object> params);
}

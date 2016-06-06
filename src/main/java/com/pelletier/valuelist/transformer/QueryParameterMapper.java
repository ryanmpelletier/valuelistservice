package com.pelletier.valuelist.transformer;

import java.util.Map;


/*
 * Remember to not be confused about this. This class is used for what Matt uses [] for in
 * his templating format. We explicitely add a parameter value to the sql. In most cases, 
 * which are safer, we let Spring do that with NamedParameterJdbcTemplate. So we
 * leave the param as :paramName.
 */


public interface QueryParameterMapper {
	public String transform(String query, Map<String, Object> params);
}

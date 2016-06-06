package com.pelletier.valuelist.adapter.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pelletier.valuelist.DataAdapter;
import com.pelletier.valuelist.DefaultValues;
import com.pelletier.valuelist.DefaultValuesInfo;
import com.pelletier.valuelist.Values;
import com.pelletier.valuelist.ValuesInfo;
import com.pelletier.valuelist.paging.PagingSupport;
import com.pelletier.valuelist.transformer.QueryParameterMapper;

public class DefaultJdbcDataAdapter implements DataAdapter<Map<String, Object>> {

	private String sql;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private QueryParameterMapper queryParameterMapper;
	private PagingSupport pagingSupport;
	
	@Override
	public Values<Map<String,Object>> query(Map<String, Object> params, ValuesInfo valuesInfo) {
		List<Map<String,Object>> results = null;
		//this is the sql that has been transformed by Velocity, note, it still has :parameter's
		//we do this step no matter what I think
		String sqlWithParams = queryParameterMapper.transform(sql, params); 
		
		if(pagingSupport != null){
			/*
			 * Might need to add on params from ValuesInfo, yeah, I think that makes sense
			 * we will always have the same names for the paging params defined, then we will just
			 * take them out of ValuesInfo and stick them on the query params. This, however, is why I do 
			 * not want to have the ValuesInfo object on the request. to me it is too much of a hassle. 
			 */
			
			//Default paging is also supported up here.
			
			/*
			 * At this point I still probably need to update totalCount. Total count is not the number they
			 * asked for, but the total number in the database I believe. This is different from my original 
			 * understanding that the totalCount was actually used to help cache support.
			 * 
			 * How do I avoid count querying, it seems like I will need to do it every time in case someone deleted
			 * or inserted since I last checked?
			 */
			
			results = namedParameterJdbcTemplate.queryForList(pagingSupport.getPagedQuery(sqlWithParams), params);
			return new DefaultValues(results, valuesInfo);
		}else{
			//in this case I return valuesInfo because the user did not supply me with one

			int totalCount = 1000;	//this will actually be found with an extra query, which sucks. 
			DefaultValuesInfo defaultValuesInfo = new DefaultValuesInfo(totalCount, 0, totalCount);
			results = namedParameterJdbcTemplate.queryForList(sqlWithParams, params);
		}
		
		return new DefaultValues(results, valuesInfo);
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void setQueryParameterMapper(QueryParameterMapper queryParameterMapper) {
		this.queryParameterMapper = queryParameterMapper;
	}

	public void setPagingSupport(PagingSupport pagingSupport) {
		this.pagingSupport = pagingSupport;
	}

	
}

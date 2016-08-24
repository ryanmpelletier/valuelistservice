package com.pelletier.valuelist.adapter.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.pelletier.valuelist.DataAdapter;
import com.pelletier.valuelist.DefaultValues;
import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.Values;
import com.pelletier.valuelist.paging.PagingSupport;
import com.pelletier.valuelist.transformer.QueryParameterMapper;
import com.pelletier.valuelist.transformer.VelocityQueryParameterMapper;
import com.pelletier.valuelist.util.AdapterConversionService;
import com.pelletier.valuelist.util.ParameterConversionService;

/** 
 * 
 * Default JDBC implementation of DataAdapter.
 * Fulfills most SQL querying purposes.
 * @author Ryan Pelletier
 *
 */
public class DefaultJdbcDataAdapter<T> implements DataAdapter<T>, InitializingBean {

	/**
	 * The sql to be run against the database.
	 */
	private String sql;
	
	/**
	 * Template which allows Spring to map params with parameter names in query.
	 */
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * Processes sql before Spring, 
	 */
	private QueryParameterMapper queryParameterMapper;
	/**
	 * Optionally convert parameters to different types before Spring runs query.
	 */
	private AdapterConversionService adapterConversionService;
	
	/**
	 * RowMapper which maps query result set to Java Objects. T is
	 * the type of the object put into the Values<T> object for this Adapter.
	 */
	private RowMapper<T> rowMapper;
	
	/**
	 * Optionally allow pagination using PagingSupport object. When left null, 
	 * full query results are returned. 
	 */
	private PagingSupport pagingSupport;
	
	/**
	 * Optionally inject defaultPagingInfo. Will be used if request does
	 * not include pagingInfo. 
	 */
	private PagingInfo defaultPagingInfo;

	@Override
	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception
	{
		if(queryParameterMapper == null)
		{
			queryParameterMapper = new VelocityQueryParameterMapper();
		}
		
		if(rowMapper == null)
		{
			 rowMapper = (RowMapper<T>) new ColumnMapRowMapper();		
		}
		
		if(adapterConversionService == null)
		{
			adapterConversionService = new ParameterConversionService();
		}
		
		if( namedParameterJdbcTemplate == null)
		{
			throw new RuntimeException("namedParameterJdbcTemplate or dataSource is required");
		}
	}
	
	@Override
	public Values<T> query(Map<String, Object> params, PagingInfo pagingInfo) {
		
		//if a default paging info is set up, and one isn't supplied, use default
		if(pagingInfo == null && defaultPagingInfo != null){
			pagingInfo = defaultPagingInfo;
		}
		
		//convert parameters if necessary
		if(adapterConversionService != null){
			 params = adapterConversionService.convert(params);
		}
		/*
		 * SQL after transformation
		 * 
		 * This is used to include sql based on the existence of a parameter, and also to 
		 * inject the values of parameters directly into the SQL without Spring.
		 */
		String sqlWithParams = queryParameterMapper.transform(sql, params);

		//need both paging support and pagingInfo to run paging
		if (pagingSupport != null && pagingInfo != null) {
			
			pagingInfo.setTotalCount(namedParameterJdbcTemplate.queryForObject(pagingSupport.getCountQuery(sqlWithParams), params, Integer.class));
			List<T> results = namedParameterJdbcTemplate.query(pagingSupport.getPagedQuery(sqlWithParams, pagingInfo), params, rowMapper);
			
			return new DefaultValues<T>(results, pagingInfo);
		} else {
			List<T> results = namedParameterJdbcTemplate.query(sqlWithParams, params, rowMapper);			
			return new DefaultValues<T>(results, null);
		}

	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setDataSource(DataSource dataSource)
	{
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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

	public void setRowMapper(RowMapper<T> rowMapper) {
		this.rowMapper = rowMapper;
	}

	public void setAdapterConversionService(AdapterConversionService adapterConversionService) {
		this.adapterConversionService = adapterConversionService;
	}

	public void setDefaultPagingInfo(PagingInfo defaultPagingInfo) {
		this.defaultPagingInfo = defaultPagingInfo;
	}

}

package com.pelletier.valuelist.adapter.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pelletier.valuelist.DataAdapter;
import com.pelletier.valuelist.DefaultPagingInfo;
import com.pelletier.valuelist.DefaultValues;
import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.Values;
import com.pelletier.valuelist.paging.PagingSupport;
import com.pelletier.valuelist.transformer.QueryParameterMapper;

public class DefaultJdbcDataAdapter implements DataAdapter<Map<String, Object>> {

	private String sql;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private QueryParameterMapper queryParameterMapper;
	private PagingSupport pagingSupport;

	@Override
	public Values<Map<String, Object>> query(Map<String, Object> params, PagingInfo pagingInfo) {
		List<Map<String, Object>> results = null;

		String sqlWithParams = queryParameterMapper.transform(sql, params);
		if (pagingSupport != null || pagingInfo == null) {
			
			DefaultPagingInfo defaultPagingInfo = new DefaultPagingInfo();
			defaultPagingInfo.setNumberPerPage(pagingInfo.getNumberPerPage());
			defaultPagingInfo.setPage(pagingInfo.getPage());
			defaultPagingInfo.setTotalCount(namedParameterJdbcTemplate.queryForObject(pagingSupport.getCountQuery(sqlWithParams), params, Integer.class));
			String pagedQuery = pagingSupport.getPagedQuery(sqlWithParams, pagingInfo);
			results = namedParameterJdbcTemplate.queryForList(pagedQuery, params);
			
			return new DefaultValues(results, defaultPagingInfo);
		} else {
			results = namedParameterJdbcTemplate.queryForList(sqlWithParams, params);
			return new DefaultValues(results, null);	//if they didn't do pagination, we don't return info about pagination
		}

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

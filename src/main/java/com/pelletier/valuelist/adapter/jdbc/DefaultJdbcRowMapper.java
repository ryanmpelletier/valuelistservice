package com.pelletier.valuelist.adapter.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
/**
 * 
 * @author Ryan Pelletier
 *
 *The default RowMapper, in case POJOs will not be used, simply wire this into
 *DefaultJdbcDataAdapter. Returns Map<String,Object> for each row.
 *
 */
public class DefaultJdbcRowMapper implements RowMapper<Map<String,Object>>{

	@Override
	public Map<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		ResultSetMetaData resultSetMetaData  = resultSet.getMetaData();
		int numColumns = resultSetMetaData.getColumnCount();
		for(int i = 0; i < numColumns; i++){
			map.put(resultSetMetaData.getColumnLabel(i + 1), resultSet.getObject(i + 1));
		}
		
		return map;
	}

	
}

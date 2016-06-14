package com.pelletier.valuelist.adapter.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Ryan Pelletier
 * 
 * RowMapper used for testing DefaultJdbcDataAdapter with TestPOJO
 *
 */
public class TestPOJOMapper implements RowMapper<TestPOJO> {

	@Override
	public TestPOJO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		TestPOJO testPOJO = new TestPOJO();
		testPOJO.setId(resultSet.getString("id"));
		testPOJO.setIntField(resultSet.getInt("intField"));
		testPOJO.setTime(resultSet.getDate("time"));
		
		return testPOJO;
	}
	
	

}

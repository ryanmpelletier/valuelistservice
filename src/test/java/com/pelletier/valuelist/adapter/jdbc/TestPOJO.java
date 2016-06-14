package com.pelletier.valuelist.adapter.jdbc;

import java.util.Date;

/**
 * @author Ryan Pelletier
 * Simple POJO used in testing DefaultJdbcAdapter with TestPOJOMapper.
 */
public class TestPOJO {
	
	private String id;
	private int intField;
	private Date time;
	
	public TestPOJO(){}
	
	public String toString(){
		return "ID: " + id + ", intField: " + intField + ", time: " + time; 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}

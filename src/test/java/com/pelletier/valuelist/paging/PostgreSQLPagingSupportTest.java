package com.pelletier.valuelist.paging;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pelletier.valuelist.PagingInfo;

public class PostgreSQLPagingSupportTest
{
	String preCountQuerySQL = "SELECT count(*) FROM (";
	String postCountQuerySQL = ")";
	String testQuery = "SELECT * FROM test";
	
	PostgreSQLPagingSupport pagingSupport;
	PagingInfo testPagingInfo;
	
	@Before
	public void setup(){
		pagingSupport = new PostgreSQLPagingSupport();
		testPagingInfo = new PagingInfo();
	}
	
	@Test
	public void testGetCountQuery(){
		assertEquals("SELECT count(*) FROM (null) AS i",pagingSupport.getCountQuery(null));
		assertEquals("SELECT count(*) FROM (SELECT * FROM test) AS i", pagingSupport.getCountQuery(testQuery));
	}
	
	
	@Test
	public void testGetPagingQuery_Page1PerPage10(){
		testPagingInfo.setNumberPerPage(10);
		testPagingInfo.setPage(1);
		
		assertEquals("SELECT * FROM test LIMIT 10 OFFSET 0", pagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
	
	@Test
	public void testGetPagingQuery_Page5PerPage10(){
		testPagingInfo.setNumberPerPage(10);
		testPagingInfo.setPage(5);
		
		assertEquals("SELECT * FROM test LIMIT 10 OFFSET 40", pagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
	
	@Test
	public void testGetPagingQuery_Page2PerPage3(){
		testPagingInfo.setNumberPerPage(3);
		testPagingInfo.setPage(2);
		
		assertEquals("SELECT * FROM test LIMIT 3 OFFSET 3", pagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
	
	@Test
	public void testGetPagingQuery_BadArguments(){
		testPagingInfo.setNumberPerPage(-1);
		testPagingInfo.setPage(50);
		
		//As long as it generates valid SQL we should be fine.
		assertEquals("SELECT * FROM test LIMIT 1 OFFSET 49", pagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
}

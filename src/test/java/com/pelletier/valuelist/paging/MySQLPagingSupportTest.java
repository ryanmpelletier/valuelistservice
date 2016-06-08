package com.pelletier.valuelist.paging;


import org.junit.Before;
import org.junit.Test;

import com.pelletier.valuelist.PagingInfo;

import static org.junit.Assert.assertEquals;


public class MySQLPagingSupportTest {
	
	String preCountQuerySQL = "SELECT count(*) as totalCount FROM (";
	String postCountQuerySQL = ") AS Alias";
	String testQuery = "SELECT * FROM test";
	
	MySQLPagingSupport mySQLPagingSupport;
	PagingInfo testPagingInfo;
	
	@Before
	public void setup(){
		mySQLPagingSupport = new MySQLPagingSupport();
		testPagingInfo = new PagingInfo();
	}
	
	@Test
	public void testGetCountQuery(){
		assertEquals(preCountQuerySQL + null + postCountQuerySQL,mySQLPagingSupport.getCountQuery(null));
		assertEquals(preCountQuerySQL + testQuery + postCountQuerySQL, mySQLPagingSupport.getCountQuery(testQuery));
	}
	
	
	@Test
	public void testGetPagingQuery(){
		testPagingInfo.setNumberPerPage(10);
		testPagingInfo.setPage(1);
		testPagingInfo.setTotalCount(10);
		
		assertEquals("SELECT * FROM test LIMIT 0, 10", mySQLPagingSupport.getPagedQuery(testQuery, testPagingInfo));		
	}

}

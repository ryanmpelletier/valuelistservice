package com.pelletier.valuelist.paging;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pelletier.valuelist.PagingInfo;

public class OraclePagingSupportTest {
	
	String preCountQuerySQL = "SELECT count(*) FROM (";
	String postCountQuerySQL = ")";
	String testQuery = "SELECT * FROM test";
	
	OraclePagingSupport oraclePagingSupport;
	PagingInfo testPagingInfo;
	
	@Before
	public void setup(){
		oraclePagingSupport = new OraclePagingSupport();
		testPagingInfo = new PagingInfo();
	}
	
	@Test
	public void testGetCountQuery(){
		assertEquals(preCountQuerySQL + null + postCountQuerySQL,oraclePagingSupport.getCountQuery(null));
		assertEquals(preCountQuerySQL + testQuery + postCountQuerySQL, oraclePagingSupport.getCountQuery(testQuery));
	}
	
	
	@Test
	public void testGetPagingQuery(){
		testPagingInfo.setNumberPerPage(10);
		testPagingInfo.setPage(1);
		testPagingInfo.setTotalCount(10);
		
		assertEquals("SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (" + testQuery + ") INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN ((1-1)*10+1) AND (1)*10",oraclePagingSupport.getPagedQuery(testQuery, testPagingInfo));	
		
		
	}
}

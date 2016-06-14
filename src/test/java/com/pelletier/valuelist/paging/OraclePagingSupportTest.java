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
	public void testGetPagingQuery_Page1PerPage10(){
		testPagingInfo.setNumberPerPage(10);
		testPagingInfo.setPage(1);
		
		assertEquals("SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (SELECT * FROM test) INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN 1 AND 10", oraclePagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
	
	@Test
	public void testGetPagingQuery_Page5PerPage10(){
		testPagingInfo.setNumberPerPage(10);
		testPagingInfo.setPage(5);
		
		assertEquals("SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (SELECT * FROM test) INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN 41 AND 50", oraclePagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
	
	@Test
	public void testGetPagingQuery_Page2PerPage3(){
		testPagingInfo.setNumberPerPage(3);
		testPagingInfo.setPage(2);
		
		assertEquals("SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (SELECT * FROM test) INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN 4 AND 6", oraclePagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
	
	@Test
	public void testGetPagingQuery_BadArguments(){
		testPagingInfo.setNumberPerPage(-1);
		testPagingInfo.setPage(50);
		
		//As long as it generates valid SQL we should be fine.
		assertEquals("SELECT * FROM (SELECT INNER.*, ROWNUM as RECORDNUM FROM (SELECT * FROM test) INNER ) WRAPPED WHERE WRAPPED.RECORDNUM BETWEEN -48 AND -50", oraclePagingSupport.getPagedQuery(testQuery, testPagingInfo));	
	}
}

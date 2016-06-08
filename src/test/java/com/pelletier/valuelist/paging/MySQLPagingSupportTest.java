package com.pelletier.valuelist.paging;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class MySQLPagingSupportTest {
	
	String preCountQuerySQL = "SELECT count(*) as totalCount FROM (";
	String postCountQuerySQL = ") AS Alias";
	MySQLPagingSupport mySQLPagingSupport;
	
	@Before
	public void setup(){
		mySQLPagingSupport = new MySQLPagingSupport();
	}
	
	@Test
	public void testMySQLPagingSupport(){
		assertEquals(true,true);
	}

}

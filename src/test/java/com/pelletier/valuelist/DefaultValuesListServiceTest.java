package com.pelletier.valuelist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class DefaultValuesListServiceTest {
	DefaultValuesListService defaultValuesListService;
	Map<String, DataAdapter> adapters;
	
	@Before
	public void setup(){
		adapters = new HashMap<>();
		adapters.put("q1", new DataAdapter<String>() {
			@Override
			public Values<String> query(Map<String, Object> params, PagingInfo pagingInfo) {
				return new DefaultValues<>(new ArrayList<String>(), pagingInfo);
			}
		});
		adapters.put("q2", null);
		defaultValuesListService = new DefaultValuesListService<>();
		defaultValuesListService.setAdapters(adapters);
	}
	
	@Test
	public void testNullAdaptersThrowsRuntimeException(){
		defaultValuesListService.setAdapters(null);
		try {
			defaultValuesListService.getValuesList("q1", new HashMap<>(), new PagingInfo());
		    fail( "Method did not throw runtime exception for missing adapters" );
		} catch (RuntimeException exception) {
			assertEquals("DataAdapters are null, please configure adapters property for DefaultValuesListService.",exception.getMessage());
		}
	}
	
	@Test
	public void testNullDataAdapterThrowsRuntimeException(){
		try {
			defaultValuesListService.getValuesList("q2", new HashMap<>(), new PagingInfo());
		    fail( "Method did not throw runtime exception for missing DataAdapter" );
		} catch (RuntimeException exception) {
			assertEquals("Query could not be found for q2, please check the Spring configuration.",exception.getMessage());
		}
		
	}

}

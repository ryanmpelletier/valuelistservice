package com.pelletier.valuelist.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.pelletier.valuelist.exception.ConversionException;
import com.pelletier.valuelist.exception.ErrorInfo;

public class ParameterConversionServiceTest {
	
	ParameterConversionService parameterConversionService;
	DefaultStringToDateConverter defaultStringToDateConverter;
	Map<String,Class> paramTypeMap;
	Map<String,Object> params;
	
	@Before
	public void setup(){
		parameterConversionService = new ParameterConversionService();
		defaultStringToDateConverter = new DefaultStringToDateConverter();
		defaultStringToDateConverter.setStringDateFormat("YYYY-MM-DD");
		
		parameterConversionService.addConverter(defaultStringToDateConverter);
		
		paramTypeMap = new HashMap<>();
		paramTypeMap.put("some_date", Date.class);
		
		parameterConversionService.setParamTypeMap(paramTypeMap);

		params = new HashMap<>();
	}

	@Test
	public void testConvertMapOfStringObject() {
		params.put("some_date", "1994-10-26");
		Map<String,Object> convertedParams = parameterConversionService.convert(params);
		assertEquals(convertedParams.get("some_date").getClass(), Date.class);
	}
	
	@Test
	public void testNullParamTypeMap(){
		params.put("some_date", "1994-10-26");
		parameterConversionService.setParamTypeMap(null);
		Map<String,Object> convertedParams = parameterConversionService.convert(params);
		assertEquals(convertedParams.get("some_date").getClass(), String.class);
	}
	
	@Test
	public void testThrowsConverterError(){
		params.put("some_date", "won't work");
		try{
			Map<String,Object> convertedParams = parameterConversionService.convert(params);
			fail("Did not throw Conversion Exception as expected.");
		}catch(Exception e){
			assertEquals(e.getClass(), ConversionException.class);
		}

	}

	@Test
	public void testConversionExceptionHasErrorInfos(){
		params.put("some_date", "won't work");
		try{
			Map<String,Object> convertedParams = parameterConversionService.convert(params);
			fail("Did not throw Conversion Exception as expected.");
		}catch(Exception e){
			List<ErrorInfo> errorInfos = ((ConversionException) e).getErrorInfos();
			assertEquals(errorInfos.size(), 1);
		}
	}
}

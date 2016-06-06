package com.pelletier.valuelist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pelletier.valuelist.exception.AdapterNotFoundException;

/**
 * Author: Ryan Pelletier
 *
 * This will be used to test the service, we will get the bean from the
 * application context.
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

		// will actually get this from the spring context
		ValueListService valueListService = (ValueListService) applicationContext.getBean("valueListService");

		Map<String, Object> queryParams = new HashMap<String, Object>();
//		queryParams.put("intField", new Integer(5));
//		queryParams.put("f2", "v2");
		Values<Map<String, Object>> result = null;
		try {
			result = (Values<Map<String, Object>>) valueListService.getValuesList("query1", queryParams,new DefaultValuesInfo(0, 0, 0));
			List<Map<String, Object>> resultSet = result.getValues();
			System.out.println(resultSet);
			ValuesInfo valuesInfo = result.getValuesInfo();
			System.out.println(valuesInfo.getTotalCount());
			System.out.println(valuesInfo.getPage());
			System.out.println(valuesInfo.getNumberPerPage());
		} catch (AdapterNotFoundException e) {
			e.printStackTrace();
		}		
	}
}

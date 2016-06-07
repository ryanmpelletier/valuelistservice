package com.pelletier.valuelist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: Ryan Pelletier
 *
 * This will be used to test the service, we will get the bean from the
 * application context.
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

		ValueListService valueListService = (ValueListService) applicationContext.getBean("valueListService");

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("intField", new Integer(5));
		Values<Map<String, Object>> result = null;
		try {
			result = (Values<Map<String, Object>>) valueListService.getValuesList("query1", queryParams,new DefaultPagingInfo(5, 2));
			List<Map<String, Object>> resultSet = result.getValues();
			PagingInfo valuesInfo = result.getValuesInfo();
			
			
//			System.out.println(resultSet);
//			System.out.println("Total Count:" + valuesInfo.getTotalCount());
//			System.out.println("Page:" + valuesInfo.getPage());
//			System.out.println("Number Per Page:" + valuesInfo.getNumberPerPage());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}		
	}
}

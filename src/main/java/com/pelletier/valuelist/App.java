package com.pelletier.valuelist;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: Ryan Pelletier
 *
 * Simple main class example for using ValueListService
 */
public class App {
	public static void main(String[] args) {
		
		final int PAGE_NUMBER = 1;
		final int NUMBER_PER_PAGE = 4;
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		ValueListService valueListService = (ValueListService) applicationContext.getBean("valueListService");

		Map<String, Object> queryParams = new HashMap<String, Object>();
//		queryParams.put("type", "gitignore");
		Values<List<File>> result = null;
		try {
			PagingInfo pagingInfo = new PagingInfo();
			pagingInfo.setNumberPerPage(NUMBER_PER_PAGE);
			pagingInfo.setPage(PAGE_NUMBER);
			
			result = (Values<List<File>>) valueListService.getValuesList("query2", queryParams, pagingInfo);
			System.out.println(result);
			
		} catch (RuntimeException e) {	
			e.printStackTrace();
		}		
	}
}


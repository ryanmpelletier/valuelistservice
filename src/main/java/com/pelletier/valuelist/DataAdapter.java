package com.pelletier.valuelist;

import java.util.Map;
/*
 * T here will be my "Transfer Object"
 * 
 */
public interface DataAdapter<T> {
	public Values<T> query(Map<String,Object> params, ValuesInfo valuesInfo);
}

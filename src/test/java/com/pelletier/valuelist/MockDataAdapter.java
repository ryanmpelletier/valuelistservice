/**
 * 
 */
package com.pelletier.valuelist;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author M.L. Wilson
 *
 */
public class MockDataAdapter<T> implements DataAdapter<T>
{
	private ArrayList<T> cache = null;

	public MockDataAdapter(T... mockdata)
	{
		cache = new ArrayList<T>();
		
		for (T value : mockdata)
		{
			this.cache.add(value);
		}
	}

	@Override
	public Values<T> query(Map<String, Object> params, PagingInfo pagingInfo)
	{
		return new DefaultValues<>(cache, pagingInfo);
	}
}

/**
 * 
 */
package com.pelletier.valuelist.adapter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.pelletier.valuelist.DataAdapter;
import com.pelletier.valuelist.DefaultValues;
import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.Values;

/**
 * @author M.L. Wilson
 *
 */
public class InMemmoryPagingDataAdapter<T> implements DataAdapter<T>, InitializingBean
{
	protected DataAdapter<T> dataAdapter = null;

	@Override
	public void afterPropertiesSet() throws Exception
	{
		if (dataAdapter == null)
		{
			throw new RuntimeException("dataAdapter is required");
		}
	}

	@Override
	public Values<T> query(Map<String, Object> params, PagingInfo pagingInfo)
	{
		Values<T> valuesHolder = dataAdapter.query(params, pagingInfo);
		if (valuesHolder != null && pagingInfo != null)
		{
			List<T> values = valuesHolder.getValues();
			pagingInfo.setTotalCount(values.size());
			
			if (pagingInfo.getNumberPerPage() > 0 && pagingInfo.getPage() > 0 && pagingInfo.getTotalCount() > (pagingInfo.getNumberPerPage() * (pagingInfo.getPage()-1)) )
			{
				if (pagingInfo.getTotalCount() > pagingInfo.getNumberPerPage())
				{
					List<T> pagedValues = values.subList((pagingInfo.getPage() - 1) * pagingInfo.getNumberPerPage(), Math.min(pagingInfo.getPage() * pagingInfo.getNumberPerPage(), pagingInfo.getTotalCount()));
					valuesHolder = new DefaultValues<T>(pagedValues, pagingInfo);
				}
			}
			else
			{
				// TODO: Not sure what to do here, but for now if a 
				// negative page or numberPerPage is passed in the 
				// list will be empty, similar to the JDBC paging support.
				valuesHolder.getValues().clear();
			}
		}

		return valuesHolder;
	}

	public void setDataAdapter(DataAdapter<T> dataAdapter)
	{
		this.dataAdapter = dataAdapter;
	}
}

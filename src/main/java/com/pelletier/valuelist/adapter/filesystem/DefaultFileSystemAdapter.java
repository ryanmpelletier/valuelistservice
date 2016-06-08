package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.pelletier.valuelist.DataAdapter;
import com.pelletier.valuelist.DefaultValues;
import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.Values;

/**
 * Simple file system adapter for directory listings.
 * This class was built as an example of the flexibility of adapters.
 * 
 * Paging is not supported.
 * 
 * @author Ryan Pelletier
 *
 */

public class DefaultFileSystemAdapter implements DataAdapter<List<File>> {
	
	/**
	 * @param params
	 * 	User must supply param "path" with String of path to list Files from
	 */
	@Override
	public Values<List<File>> query(Map<String, Object> params, PagingInfo pagingInfo) {
		if(params.get("path") == null){
			//It would be nice to log some stuff here
			params.put("path", ".");
		}
		File[] files = new File((String) params.get("path")).listFiles();
		pagingInfo.setTotalCount(files.length);
		pagingInfo.setPage(1);
		pagingInfo.setNumberPerPage(files.length);
		return new DefaultValues(Arrays.<File>asList(files), pagingInfo);
	}

}

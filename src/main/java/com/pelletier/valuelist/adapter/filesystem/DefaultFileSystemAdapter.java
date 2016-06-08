package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
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
 * 
 * The user should be able to configure fileFilters to apply
 * to the files which are retrieved. 
 * So basically what will happen, we get the path, and get our
 * list of files.
 * 
 * Then after we get the list of files, we pass it through the filter chain
 * file filter will be an interface which has one method, filter
 * 
 * @author Ryan Pelletier
 *
 */

public class DefaultFileSystemAdapter implements DataAdapter<List<File>> {
	
	private String path = "/";
	private String baseDirectory = ".";
	private FileFilter fileFilter;
	
	/**
	 * @param params
	 * 	User must supply param "path" with String of path to list Files from
	 */
	@Override
	public Values<List<File>> query(Map<String, Object> params, PagingInfo pagingInfo) {
		
		//I am trying to figure out how to do my paging info as a filter,
		//I would also really like to figure out how to filter using java's FileFilter

		
		List<File> files;
		if(params.get("path") != null){
			files = new LinkedList<File>(Arrays.asList(new File((String) baseDirectory + params.get("path")).listFiles()));
		}else{
			files = new LinkedList<File>(Arrays.asList(new File(path).listFiles()));
		}
		
		if(fileFilter != null){
			fileFilter.filter(files, params);
		}
		
		//now we apply and go through filters	
//		pagingInfo.setTotalCount(files.length);
//		pagingInfo.setPage(1);
//		pagingInfo.setNumberPerPage(files.length);
		
		return new DefaultValues(files, pagingInfo);
	}

	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setFileFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}
	
	

}

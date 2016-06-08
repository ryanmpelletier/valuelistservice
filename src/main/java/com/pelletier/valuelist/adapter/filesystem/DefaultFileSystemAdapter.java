package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
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
	
	/**
	 * @param params
	 * 	User must supply param "path" with String of path to list Files from
	 */
	@Override
	public Values<List<File>> query(final Map<String, Object> params, PagingInfo pagingInfo) {
		String absolutePath = baseDirectory + ((params.get("path") != null) ? params.get("path") : path);  
		
		//filter
		List<File> files = new LinkedList<File>(Arrays.asList(new File(absolutePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if(params.get("regex") != null){
					return file.getAbsolutePath().matches((String) params.get("regex"));
				}
				return true;
			}
		})));
		
		pagingInfo.setTotalCount(files.size());
		
		
		/*
		 * I should be able to sort by name OR by date.
		 * I cannot sort by both. If both params are included, files will only be
		 * sorted by name.
		 */
		
		//sort
		files.sort(new Comparator<File>() {

			@Override
			public int compare(File file1, File file2) {
				if(params.get("name") != null){
					if(((String) params.get("name")).equalsIgnoreCase("ASC")){
						return file1.compareTo(file2);
					}else if(((String) params.get("name")).equalsIgnoreCase("DESC")){
						return file2.compareTo(file1);
					}else{
						return 0;
					}
				}else if(params.get("date") != null){
					if(((String) params.get("date")).equalsIgnoreCase("ASC")){
						return (int)(file1.lastModified() - file2.lastModified());
					}else if(((String) params.get("date")).equalsIgnoreCase("DESC")){
						return (int)(file2.lastModified() - file1.lastModified());
					}else{
						return 0;
					}
				}
				return 0;
			}
			
		});
		
		//page
		if(pagingInfo != null){
			FilePager.filter(files, pagingInfo);
		}
				
		return new DefaultValues(files, pagingInfo);
	}

	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

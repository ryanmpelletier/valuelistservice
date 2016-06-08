package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.List;
import java.util.Map;

public class FileFilterChain implements FileFilter {
	
	
	private List<FileFilter> fileFilters;

	@Override
	public void filter(List<File> files, Map<String, Object> params) {
		for(FileFilter fileFilter : fileFilters){
			fileFilter.filter(files, params);
		}
	}

	public void setFileFilters(List<FileFilter> fileFilters) {
		this.fileFilters = fileFilters;
	}
	
}

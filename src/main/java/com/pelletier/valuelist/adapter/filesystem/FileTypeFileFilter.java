package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileTypeFileFilter implements FileFilter{
	
	//key and default value for query, likely I will want to have an Abstract class of this to 
	//suppor the idea of key and defaultValue being able to be configured in Spring
	private String key = "type";
	private String defaultValue;

	@Override
	public void filter(List<File> files, Map<String, Object> params) {
		
		Iterator<File> iterator = files.iterator();
		while(iterator.hasNext()){
			File file = iterator.next();
			String fileExtension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
			if(file.isDirectory() || (file.isFile() && !fileExtension.equals(getType(params)))){
				iterator.remove();
			}
		}
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getType(Map<String, Object> params){
		if(params.get(key) != null){
			return (String) params.get(key);
		}
		if(defaultValue != null){
			return defaultValue;
		}
		return null;
	}

}

package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PagingFileFilter implements FileFilter {

	@Override
	public void filter(List<File> files, Map<String, Object> params) {		
		int count = 0;
		Iterator<File> iterator = files.iterator();
		
		int firstItem = ((int) params.get("page") - 1) * (int) params.get("numberPerPage");
		int lastItem = firstItem + (int) params.get("numberPerPage");
		
		while(iterator.hasNext()){
			iterator.next();
			if(!(count >= firstItem && count < lastItem)){
				iterator.remove();
			}
			count++;
		}
	}

}

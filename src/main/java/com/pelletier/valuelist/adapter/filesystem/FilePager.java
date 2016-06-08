package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.pelletier.valuelist.PagingInfo;

public class FilePager{

	public static void filter(List<File> files, PagingInfo pagingInfo) {		
		int count = 0;
		Iterator<File> iterator = files.iterator();
		
		int firstItem = (pagingInfo.getPage() - 1) * pagingInfo.getNumberPerPage();
		int lastItem = firstItem + pagingInfo.getNumberPerPage();
		
		while(iterator.hasNext()){
			iterator.next();
			if(!(count >= firstItem && count < lastItem)){
				iterator.remove();
			}
			count++;
		}
	}

}

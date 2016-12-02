package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.adapter.misc.InMemmoryPagingDataAdapter;

/**
 * @see InMemmoryPagingDataAdapter
 * 
 * @deprecated Use the InMemmoryPagingDataAdapter instead.
 */
public class FilePager{

	/**
	 * 
	 * @param files
	 * List of files to page.
	 * 
	 * @param pagingInfo
	 * Requested pages and items per page.
	 */
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

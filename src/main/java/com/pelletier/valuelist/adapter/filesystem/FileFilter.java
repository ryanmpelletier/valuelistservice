package com.pelletier.valuelist.adapter.filesystem;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface FileFilter {
	
	public void filter(List<File> files, Map<String,Object> params);

}

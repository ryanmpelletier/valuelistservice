package com.pelletier.valuelist.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pelletier.valuelist.ConversionException;
import com.pelletier.valuelist.ValueListService;
import com.pelletier.valuelist.ConversionException.ErrorInfo;

public class AbstractListServiceController
{
	/**
	 * The autowired valueListService.
	 */
    @Autowired
    protected ValueListService valueListService;
    
    /**
     * 
     * @param conversionException
     * The ConversionException that was thrown within the controller.
     * @return
     * Return an Map<String,Map<String,String>> which contains exception information. Top level map will 
     * include an entry for each parameter name which could not be converted, with its value being another map
     * which includes simple_message and exception_message.
     */
    @ExceptionHandler(ConversionException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,Map<String,String>> conversionExceptionHandler(ConversionException conversionException){
    	Map<String,Map<String,String>> errorInfoMap = new HashMap<>();
    	
    	for(ErrorInfo errorInfo : conversionException.getErrorInfos()){
    		Map<String,String> messageMap = new HashMap<>();
    		messageMap.put("simple_message", errorInfo.getSimpleMessage());
    		messageMap.put("exception_message", errorInfo.getExceptionMessage());
    		errorInfoMap.put(errorInfo.getKey(), messageMap);
    	}
    	
        return errorInfoMap;
    }
   
    /**
     * 
     * @param exception
     * The Exception thrown within the controller, (does not catch ConversionException)
     * 
     * @return
     * Return an Map<String,Map<String,String>> which contains exception information.
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Map<String,String>> exceptionHandler(Exception exception){
    	
    	Map<String,Map<String,String>> errorInfoMap = new HashMap<>();

		Map<String,String> messageMap = new HashMap<>();
		messageMap.put("simple_message", "The server had an error while processing your request.");
		messageMap.put("exception_message", exception.getMessage());
		errorInfoMap.put("internal_server_error", messageMap);
    	
		return errorInfoMap;
    }
}

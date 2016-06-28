package com.pelletier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.ValueListService;
import com.pelletier.valuelist.Values;
import com.pelletier.valuelist.exception.ConversionException;
import com.pelletier.valuelist.exception.ErrorInfo;

/**
 * Created by Ryan Pelletier on 6/10/2016.
 *
 *
 *
 *This class is intended to be included in a Spring project which uses component-scan and annotation-driven in the Spring XML file.
 *
 *
 * Spring should pick up and wire in the configured ValueListService into this controller,
 * and the controller will accept HTTP GET requests to /valueslistservice/values relative URL.
 */
@Controller
public class ValueListServiceController {
	
	/**
	 * The autowired valueListService.
	 */
    @Autowired
    ValueListService valueListService;

    /**
     * 
     * @param request
     * The HttpServletRequest object.
     * 
     * @param response
     * The HttpServletResponse object.
     * 
     * @return
     * Returns a Values object which will contain a list of whatever the valueListService is configured to return.
     */
    @RequestMapping(value = "/valuelistservice/values", method = RequestMethod.GET)
    @ResponseBody
    public Values<? extends Object> getValueList(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> params = new HashMap<String, Object>();

        for(Object key : request.getParameterMap().keySet()){
            String paramValue = ((String[])request.getParameterMap().get(key))[0];
            params.put((String)key,paramValue);
        }
        PagingInfo pagingInfo = null;

        if(params.get("page") != null && params.get("numberPerPage") != null){
            pagingInfo = new PagingInfo();
            pagingInfo.setPage(Integer.parseInt((String)params.get("page")));
            pagingInfo.setNumberPerPage(Integer.parseInt((String)params.get("numberPerPage")));
        }

        return valueListService.getValuesList((String) params.get("valueListQuery"), params, pagingInfo);
    }
    
    
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

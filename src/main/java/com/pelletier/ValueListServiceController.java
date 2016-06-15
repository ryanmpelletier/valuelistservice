package com.pelletier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.ValueListService;
import com.pelletier.valuelist.Values;

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
@RestController
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
    @RequestMapping(value = "/valueslistservice/values", method = RequestMethod.GET)
    public Values<? extends Object> getValueList(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
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
}

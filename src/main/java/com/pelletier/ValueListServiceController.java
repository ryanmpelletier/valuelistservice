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
 * So basically, this class is going to get torn out of here, and be put in my jar.
 *
 * Then, I should be able to configure in XML the request mapping value for this jar.
 * Thats it.
 *
 * So the first step would be just moving this class as it is to my other project, then repackaging the
 * jar and putting it back in here with the same component scan thing.
 *
 */
@RestController
public class ValueListServiceController {
	
    @Autowired
    ValueListService valueListService;

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

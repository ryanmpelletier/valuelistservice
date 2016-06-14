package com.pelletier.valuelist.util;

import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import com.pelletier.valuelist.adapter.AdapterConversionService;

/**
 * 
 * @author Ryan Pelletier
 * 
 * This service allows the conversion of one type to another.
 * It is useful to inject this into a DataAdapter when it needs to convert
 * data before querying. For example, the DefaultJdbcDataAdapter can use this
 * to turn String values into different types before Spring runs queries.
 *
 */
public class ParameterConversionService extends DefaultConversionService implements AdapterConversionService {
	
	/**
	 * This is a map of parameter names, and Classes. If the String param start_date must
	 * be changed to java.util.Date, the entry would be ("start_date", Class.forName("java.util.Date")).
	 * 
	 * Conveniently, in Spring, Class can be configured with the full name of the class.
	 * 
	 */
    private Map<String,Class> paramTypeMap;
    
    /**
     * This is a list of Converter<S, T> objects, which will be 
     * added to the current list of converters in DefaultConversionService.
     */
    private List<Converter> converters;

    
    /**
     * @param paramKey
     * 
     * 
     * @param paramValue
     * 
     * 
     * @return 
     * 
     * Attempts to convert the type if necessary, if no conversion is needed
     * returns the current object in the map.
     */
    @Override
    public <T> T convertIfNeeded(String paramKey, Object paramValue){
    	
        if(paramTypeMap.get(paramKey) != null){
            return (T) convert(paramValue, paramTypeMap.get(paramKey));
        }
        return (T) paramValue;
    }

    public void setParamTypeMap(Map<String, Class> paramTypeMap) {
        this.paramTypeMap = paramTypeMap;
    }

    /**
     * Adds converters to the registry of converters in DefaultConversionService
     * 
     * @param converters
     * converters to add to internal registry
     */
    public void setConverters(List<Converter> converters) {
        this.converters = converters;
        for(Converter converter : this.converters){
            addConverter(converter);
        }
    }
}

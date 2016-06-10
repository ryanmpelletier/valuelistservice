package com.pelletier.valuelist.util;

import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

public class ParameterConversionService extends DefaultConversionService {
	
    /*
     * ParamTypeMap and converters will be able to be optionally configured
     * to inherit from a global paramTypeMap and converters list using
     * MapFactoryBean and ListFactoryBean, respectively.  
     */
	
    private Map<String,Class> paramTypeMap;
    private List<Converter> converters;

    //changed this method to take an Object paramValue since we don't
    //want to assume this service is wrapped in a REST endpoint where param values are all strings
    public <T> T convertIfNeeded(String paramKey, Object paramValue){
        if(paramTypeMap.get(paramKey) != null){
            return (T) convert(paramValue, paramTypeMap.get(paramKey));
        }
        return (T) paramValue;
    }

    public void setParamTypeMap(Map<String, Class> paramTypeMap) {
        this.paramTypeMap = paramTypeMap;
    }

    public void setConverters(List<Converter> converters) {
        this.converters = converters;
        for(Converter converter : this.converters){
            addConverter(converter);
        }
    }
}

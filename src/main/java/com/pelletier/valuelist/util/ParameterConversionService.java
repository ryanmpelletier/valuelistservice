package com.pelletier.valuelist.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import com.pelletier.valuelist.exception.ConversionException;
import com.pelletier.valuelist.exception.ErrorInfo;

/**
 * 
 * @author Ryan Pelletier
 * 
 *         This service allows the conversion of one type to another. It is
 *         useful to inject this into a DataAdapter when it needs to convert
 *         data before querying. For example, the DefaultJdbcDataAdapter can use
 *         this to turn String values into different types before Spring runs
 *         queries.
 *
 */
public class ParameterConversionService extends DefaultConversionService implements AdapterConversionService {

	/**
	 * This is a map of parameter names, and Classes. If the String param
	 * start_date must be changed to java.util.Date, the entry would be
	 * ("start_date", Class.forName("java.util.Date")).
	 * 
	 * Conveniently, in Spring, Class can be configured with the full name of
	 * the class.
	 * 
	 */
	private Map<String, Class> paramTypeMap;

	/**
	 * This is a list of Converter<S, T> objects, which will be added to the
	 * current list of converters in DefaultConversionService.
	 */
	private List<Converter> converters;
	
	private ConversionException conversionException;

	/**
	 * @param params
	 * Map of parameters, of which entries may be converted.
	 * 
	 * @return
	 * Return a new map, with entries which have possibly been converted.
	 * 
	 * 
	 * So in here I will basically need to collect errors, then at the end, if there are errors, I will need to throw my custom error.
	 */
	@Override
	public <T> Map<String,T> convert(Map<String, Object> params) {

		Map<String,T> returnMap = new HashMap<String, T>();
		
		for (String paramKey : params.keySet()) {
			if (paramTypeMap != null && paramTypeMap.get(paramKey) != null) {
				try{
					returnMap.put(paramKey, (T) convert(params.get(paramKey), paramTypeMap.get(paramKey)));
				}catch(RuntimeException exception){
					//add exception to ConversionException
					if(this.conversionException == null){
						conversionException = new ConversionException();
					}
					conversionException.addErrorInfo(new ErrorInfo(ConversionException.INVALID, paramKey,exception));
				}
			}else{
				returnMap.put(paramKey, (T) params.get(paramKey));
			}
		}
		if(conversionException != null){
			throw conversionException;
		}
		return returnMap;
	}

	public void setParamTypeMap(Map<String, Class> paramTypeMap) {
		this.paramTypeMap = paramTypeMap;
	}

	/**
	 * Adds converters to the registry of converters in DefaultConversionService
	 * 
	 * @param converters
	 *            converters to add to internal registry
	 */
	public void setConverters(List<Converter> converters) {
		this.converters = converters;
		for (Converter converter : this.converters) {
			addConverter(converter);
		}
	}
}

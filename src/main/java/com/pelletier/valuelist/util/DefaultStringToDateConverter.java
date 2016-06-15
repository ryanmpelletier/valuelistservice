package com.pelletier.valuelist.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
/**
 * Provides simple String to java.util.Date conversion.
 * 
 * @author Ryan Pelletier
 *
 */
public class DefaultStringToDateConverter implements Converter<String, Date> {
	
	private DateFormat dateFormat;
	final static Logger logger = Logger.getLogger(DefaultStringToDateConverter.class);


	/**
	 * @param source
	 * Converts source into java.util.Date using SimpleDateFormat
	 * 
	 */
	@Override
	public Date convert(String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
        	logger.error("Could not convert create date from " + source + ".", e);
            return null;
        }
	}
	
	
    public void setStringDateFormat(String stringDateFormat) {
        dateFormat = new SimpleDateFormat(stringDateFormat);
    }
}

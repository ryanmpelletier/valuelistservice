package com.pelletier.valuelist.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DefaultStringToDateConverter implements Converter<String, Date> {
	
	private String stringDateFormat;
	private DateFormat dateFormat;

	@Override
	public Date convert(String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
        	//TODO: [MLW] Use a logger. We need to talk about conversion errors.
            e.printStackTrace();
            return null;
        }
	}
	
	
    public void setStringDateFormat(String stringDateFormat) {
        this.stringDateFormat = stringDateFormat;
        dateFormat = new SimpleDateFormat(this.stringDateFormat);
    }
}

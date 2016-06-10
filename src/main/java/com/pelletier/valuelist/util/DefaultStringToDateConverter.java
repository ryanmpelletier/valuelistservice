package com.pelletier.valuelist.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DefaultStringToDateConverter implements Converter<String, Date> {
	
	private String stringDateFormat;

	@Override
	public Date convert(String source) {
        DateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	
    public void setStringDateFormat(String stringDateFormat) {
        this.stringDateFormat = stringDateFormat;
    }
}

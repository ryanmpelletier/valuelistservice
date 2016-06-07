package com.pelletier.valuelist.transformer;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityQueryParameterMapper implements QueryParameterMapper {
	
	@Override
	public String transform(String query, Map<String, Object> params) {
	   	 	
		Velocity.init();
		
		VelocityContext velocityContext = new VelocityContext();
		
		for(String key : params.keySet()){
			velocityContext.put(key, params.get(key));
		}
		
	
		StringWriter stringWriter = new StringWriter();
		
		//this takes an extra string argument that I don't really care about
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		
		return stringWriter.toString();
	}

}

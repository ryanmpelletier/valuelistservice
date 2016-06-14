package com.pelletier.valuelist.transformer;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
/**
 * 
 * 
 * Depends on Apache Velocity
 * Uses velocity to map params, evaluate velocity expressions, and otherwise handle 
 * configured parameters the provided SQL.
 * 
 * @author Ryan Pelletier
 */
public class VelocityQueryParameterMapper implements QueryParameterMapper {
	
	/**
	 * @param query
	 * A SQL query to be run through the Velocity engine.
	 * 
	 * @param params
	 * Parameters which Velocity will attempt to apply to the provided SQL.
	 * 
	 * @return 
	 * Return Velocity processed SQL.
	 */
	@Override
	public String transform(String query, Map<String, Object> params) {
	   	 	
		Velocity.init();
		
		VelocityContext velocityContext = new VelocityContext();
		
		for(String key : params.keySet()){
			velocityContext.put(key, params.get(key));
		}
		
		StringWriter stringWriter = new StringWriter();
		
		/*
		 * this takes an extra string argument that I don't really care about
		 * it has something to do with logging, so I simply put "log"
		 */
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		
		return stringWriter.toString();
	}

}

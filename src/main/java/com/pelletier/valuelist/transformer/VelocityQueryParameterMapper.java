package com.pelletier.valuelist.transformer;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
/**
 * 
 * @author Ryan Pelletier
 * 
 * Depends on Apache Velocity
 * Uses velocity to map params, evaluate velocity expressions, and otherwise handle 
 * configured parameters the provided SQL.
 */
public class VelocityQueryParameterMapper implements QueryParameterMapper {
	
	@Override
	public String transform(String query, Map<String, Object> params) {
	   	 	
		Velocity.init();
		
		VelocityContext velocityContext = new VelocityContext();
		
		/*
		 * put parameters onto velocity context, note that if velocity does not find a param in a String
		 * it will leave the param alone, for example if $test was not in the param map in the velocity context,
		 * then the String "This is a $test" would be evaluated by velocity as "This is a $test"
		 */
		
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

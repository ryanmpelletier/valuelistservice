import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Before;
import org.junit.Test;

public class VelocityQueryParameterMapperTest {
	
	Map<String, Object> params = new HashedMap();
	VelocityContext velocityContext;
	StringWriter stringWriter;
	
	@Before
	public void setup(){
		params.put("key1", "value1");
		params.put("key2", "value2");
		params.put("key3", new String("I'm a string"));
		params.put("key4", new Object(){
			@Override
			public String toString(){
				return "object";	
			}
		});
		params.put("key5", null);

		
		Velocity.init();
		velocityContext = new VelocityContext();
		stringWriter = new StringWriter();
		
		for(String key : params.keySet()){
			velocityContext.put(key, params.get(key));
		}
	}
	
	
	/*
	 * Test to see what velocity does with object as param.
	 * Test method calls in velocity template
	 */
	
	
	@Test
	public void testSimpleQueryMapping(){
		String query = "SELECT * FROM test WHERE col1 = $key1";
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		assertEquals("SELECT * FROM test WHERE col1 = value1", stringWriter.toString());
		
		stringWriter = new StringWriter();
		query = "SELECT * FROM test WHERE col1 = $key1 AND col2 = $key2";
		
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		assertEquals("SELECT * FROM test WHERE col1 = value1 AND col2 = value2", stringWriter.toString());
	}
	
	@Test
	public void testComplexQueryMapping(){
		String query = "SELECT * FROM test #if($key1)WHERE col1 > -1 order by $key1#end";
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		assertEquals("SELECT * FROM test WHERE col1 > -1 order by value1", stringWriter.toString());
	}
	
	@Test
	public void testQueryWithNullParam(){
		String query = "SELECT * FROM test WHERE col1 = $key5";
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		assertEquals("SELECT * FROM test WHERE col1 = $key5", stringWriter.toString());
	}
	
	@Test
	public void testQueryWithObject(){
		String query = "SELECT * FROM test WHERE col1 = $key3";
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		assertEquals("SELECT * FROM test WHERE col1 = I'm a string", stringWriter.toString());
	}
	
	@Test
	public void testQueryWithObjectMethodCall(){
		String query = "SELECT * FROM test WHERE col1 = $key4.toString()";
		Velocity.evaluate(velocityContext, stringWriter, "log", query);
		assertEquals("SELECT * FROM test WHERE col1 = object", stringWriter.toString());
	}
}

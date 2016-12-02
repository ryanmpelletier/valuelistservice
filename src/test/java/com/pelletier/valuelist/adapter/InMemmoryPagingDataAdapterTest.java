/**
 * 
 */
package com.pelletier.valuelist.adapter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pelletier.valuelist.MockDataAdapter;
import com.pelletier.valuelist.PagingInfo;
import com.pelletier.valuelist.Values;
import com.pelletier.valuelist.adapter.InMemmoryPagingDataAdapter;

/**
 * @author M.L. Wilson
 */
public class InMemmoryPagingDataAdapterTest
{

	@Test
	public void testNullPagingInfo()
	{
		String[] data = new String[]
		{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		InMemmoryPagingDataAdapter<String> adapter = new InMemmoryPagingDataAdapter<String>();
		adapter.setDataAdapter(new MockDataAdapter<String>(data));

		Values<String> values = adapter.query(null, null);
		
		assertArrayEquals(data, values.getValues().toArray());
	}
	
	@Test
	public void testSimplePagingPage1()
	{
		InMemmoryPagingDataAdapter<String> adapter = new InMemmoryPagingDataAdapter<String>();
		adapter.setDataAdapter(new MockDataAdapter<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

		Values<String> values = adapter.query(null, new PagingInfo(3,1));
		
		assertEquals(10, values.getValuesInfo().getTotalCount());
		assertArrayEquals(new String[]{"0", "1", "2"}, values.getValues().toArray());
	}
	
	@Test
	public void testSimplePagingPage2()
	{
		InMemmoryPagingDataAdapter<String> adapter = new InMemmoryPagingDataAdapter<String>();
		adapter.setDataAdapter(new MockDataAdapter<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

		Values<String> values = adapter.query(null, new PagingInfo(3,2));
		
		assertEquals(10, values.getValuesInfo().getTotalCount());
		assertArrayEquals(new String[]{"3", "4", "5"}, values.getValues().toArray());
	}
	
	@Test
	public void testSimplePagingPage3()
	{
		InMemmoryPagingDataAdapter<String> adapter = new InMemmoryPagingDataAdapter<String>();
		adapter.setDataAdapter(new MockDataAdapter<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

		Values<String> values = adapter.query(null, new PagingInfo(3,3));
		
		assertEquals(10, values.getValuesInfo().getTotalCount());
		assertArrayEquals(new String[]{"6", "7", "8"}, values.getValues().toArray());
	}
	
	@Test
	public void testSimplePagingPage4()
	{
		InMemmoryPagingDataAdapter<String> adapter = new InMemmoryPagingDataAdapter<String>();
		adapter.setDataAdapter(new MockDataAdapter<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

		Values<String> values = adapter.query(null, new PagingInfo(3,4));
		
		assertEquals(10, values.getValuesInfo().getTotalCount());
		assertArrayEquals(new String[]{"9"}, values.getValues().toArray());
	}
	
	@Test
	public void testSimplePagingPage5()
	{
		InMemmoryPagingDataAdapter<String> adapter = new InMemmoryPagingDataAdapter<String>();
		adapter.setDataAdapter(new MockDataAdapter<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

		Values<String> values = adapter.query(null, new PagingInfo(3,5));
		
		assertEquals(10, values.getValuesInfo().getTotalCount());
		assertArrayEquals(new String[]{}, values.getValues().toArray());
	}
	
	@Test
	public void testNegativePage()
	{
		InMemmoryPagingDataAdapter<String> adapter = new InMemmoryPagingDataAdapter<String>();
		adapter.setDataAdapter(new MockDataAdapter<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

		Values<String> values = adapter.query(null, new PagingInfo(3,-2));
		
		//Result should be empty.
		assertEquals(10, values.getValuesInfo().getTotalCount());
		assertArrayEquals(new String[]{}, values.getValues().toArray());
	}

}

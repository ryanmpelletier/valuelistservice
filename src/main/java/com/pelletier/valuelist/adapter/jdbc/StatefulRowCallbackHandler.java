package com.pelletier.valuelist.adapter.jdbc;

import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

/** Needed as most RowCallbackHandler(s) are not thread safe and are created per request.
 * 
 * @author M.L. Wilson
 *
 * @param <T> Type of Object returned.
 */
public interface StatefulRowCallbackHandler<T> extends RowCallbackHandler
{
	/** Returns all the values gathered from this CallbackHandler
	 * 
	 * @return all the values gathered from this CallbackHandler
	 */
	List<T> getValues();
}
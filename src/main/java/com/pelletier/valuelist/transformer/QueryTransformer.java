package com.pelletier.valuelist.transformer;

/*
 * There really are two different kinds of things I might need to do to a query.
 * I might need to directly insert values if the parameter exists, or, I might need to include
 * certain lines of sql if a parameter exists.
 * 
 * This is the class which I will use simply to transform a query, I cannot actually think of a use for this except
 * pagination. (Which I may or may not use it for)
 * 
 * I am starting to think I won't be able to use it for pagination since the pagination support just does too much. 
 */

public interface QueryTransformer {
	public String transform(String query);
}

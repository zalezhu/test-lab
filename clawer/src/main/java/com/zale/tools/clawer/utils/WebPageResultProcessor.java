/**
 * 
 */
package com.zale.tools.clawer.utils;

/**
 * the search processor callback interface for web search
 * @author Zale
 *
 */
public interface WebPageResultProcessor<T>{
	/**
	 * the process method defines what needs to be done when a web search returns valid result
	 */
	void process(T result) throws Exception;
}

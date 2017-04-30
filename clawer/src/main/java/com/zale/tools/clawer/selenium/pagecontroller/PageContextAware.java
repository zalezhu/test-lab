/**
 * 
 */
package com.zale.tools.clawer.selenium.pagecontroller;


/**
 * @author Zale
 *
 */
public interface PageContextAware<T extends PageContext> {
	/**
	 * get the context of the page
	 * @return
	 */
	public T getContext();
}

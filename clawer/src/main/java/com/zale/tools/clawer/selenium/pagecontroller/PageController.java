/**
 * 
 */
package com.zale.tools.clawer.selenium.pagecontroller;

import com.zale.tools.clawer.selenium.pagecontroller.pagemodel.PageModel;

/**
 * The page requires login
 * 
 * @author Zale
 *
 */
public interface PageController<R, T extends PageContext, M extends PageModel> extends PageContextAware<T>{
	
	/**
	 * login the page
	 */
	public R action(M model);
	
}

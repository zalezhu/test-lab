/**
 * 
 */
package com.zale.tools.clawer.selenium.pagecontroller;

import org.openqa.selenium.WebDriver;
/**
 *
 * @Author Zale
 * @Date 2017/5/2 下午4:59
 *
 */
public final class SeleniumPageContext implements PageContext {
	
	private final WebDriver driver;
	
	/**
	 * get the web driver instance
	 * @return
	 */
	public WebDriver getWebDriver(){
		return this.driver;
	}
	
	public SeleniumPageContext(WebDriver driver){
		this.driver = driver;
	}
}

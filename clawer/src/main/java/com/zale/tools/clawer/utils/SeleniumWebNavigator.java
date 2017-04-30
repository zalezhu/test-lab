/**
 * 
 */
package com.zale.tools.clawer.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.function.Function;

/**
 * 
 * @Author Zale
 * @Date 2017/4/16 下午11:42
 *
 */
public abstract class SeleniumWebNavigator {
	
	private static final Logger logger = Logger.getLogger(SeleniumWebNavigator.class);
	
	public static final int DEFAULT_NAVIGATION_RETRIES = 1;
	
	private final WebDriver webDriver;
    
	private final String url;
	
	public SeleniumWebNavigator(String url, WebDriver webDriver){
		this.url = url;
		this.webDriver = webDriver;
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getUrl() {
		return url;
	}
	
	/**
	 * go to the expected url
	 * @return
	 */
	public void process(WebPageResultProcessor<String> processor) throws PageNotLoadedException{
		if(logger.isInfoEnabled()){
			logger.info("navigate to "+getUrl());
		}
		
		//go to the url
		SeleniumControllerUtil.navigate(getWebDriver(), getUrl(), 1);
		
		//dont retry
		SeleniumControllerUtil.waitForResponse(getWebDriver(), 40, getExpectedConditionOnPageLoadedSuccess(), false);
		
		//parse the page regardless of page loading
		try{
			processor.process(getWebDriver().getPageSource());
		}catch(Exception ex){
			if(logger.isInfoEnabled()){
				logger.warn("failed to parse page source into document.", ex);
			}
			
			throw new PageNotLoadedException("failed to parse users.", ex);
		}
	}
	
	/**
	 * get the expected condition on page loaded successfully
	 * @return
	 */
	protected abstract Function<WebDriver,?> getExpectedConditionOnPageLoadedSuccess();
	
	/**
	 * the page could not be loaded successfully in responsive time manner
	 * @author Zale
	 *
	 */
	public static class PageNotLoadedException extends Exception{
		/**
		 * @param message
		 * @param ex
		 */
		public PageNotLoadedException(String message, Exception ex) {
			super(message, ex);
		}

		/**
		 * @param message
		 */
		public PageNotLoadedException(String message) {
			super(message);
		}
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -197183069273294154L;
	}

}

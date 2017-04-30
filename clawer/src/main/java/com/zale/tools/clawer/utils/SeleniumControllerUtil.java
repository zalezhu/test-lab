/**
 * 
 */
package com.zale.tools.clawer.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 *
 * @Author Zale
 * @Date 2017/4/16 下午11:18
 *
 */
public abstract class SeleniumControllerUtil {
	
	private static final Logger logger = Logger.getLogger(SeleniumControllerUtil.class);
	
	/**
	 * default load page time in 60 seconds
	 */
	private static final long DEFAULT_LOAD_PAGE_TIMEOUT_SECONDS = 60;
	
	/**
	 * default load page time in 60 seconds
	 */
	private static final long MAXIMUM_LOAD_PAGE_TIMEOUT_SECONDS = 80;
	
	private static final ExecutorService NAVIGATOR_EXECUTOR = Executors.newCachedThreadPool();
	
	/**
	 * wait for response on a particular request
	 * @param driver
	 * @param seconds
	 * @param condition
	 * @param retry
	 * @return
	 */
	public static boolean waitForResponse(WebDriver driver, int seconds, Function<WebDriver,?> condition, boolean retry, String retryOnPage){
		boolean hasResponse = false;
		int maxRetries = 3;
		int currentRetry = 1;
		while(currentRetry < maxRetries){
			try{
				new WebDriverWait(driver, seconds).until(condition);
				
				if(logger.isDebugEnabled()){
					logger.debug("entered page "+driver.getTitle() +" at "+driver.getCurrentUrl());
				}

				hasResponse = true;
				break;
			}catch(Throwable th){
				if(logger.isDebugEnabled()){
					logger.debug("NoResponse from "+driver.getCurrentUrl());
				}
				if(retry){
					if(logger.isInfoEnabled()){
						logger.warn("NoResponse from "+driver.getCurrentUrl()+" will retry for #"+currentRetry);
					}
					currentRetry ++;
					
					//reloading the page
					SeleniumControllerUtil.navigate(driver, retryOnPage, 1);
				}else{
					if(logger.isInfoEnabled()){
						logger.warn("NoResponse from requested url "+driver.getCurrentUrl());
					}
					break;
				}
			}
		}
		
		if(currentRetry >= maxRetries){
			if(logger.isInfoEnabled()){
				logger.warn("NoResponse from requested url "+driver.getCurrentUrl());
			}
		}
		
		return hasResponse;
	}
	
	/**
	 * wait for response on a particular request
	 * @param driver
	 * @param seconds
	 * @param condition
	 * @param retry
	 * @return
	 */
	public static boolean waitForResponse(WebDriver driver, int seconds, Function<WebDriver,?> condition, boolean retry){
		boolean hasResponse = false;
		int maxRetries = 3;
		int currentRetry = 1;
		while(currentRetry < maxRetries){
			try{
				new WebDriverWait(driver, seconds).until(condition);
				
				if(logger.isDebugEnabled()){
					logger.debug("entered page "+driver.getTitle() +" at "+driver.getCurrentUrl());
				}

				hasResponse = true;
				break;
			}catch(Throwable th){
				if(logger.isDebugEnabled()){
					logger.debug("NoResponse from "+driver.getCurrentUrl());
				}
				if(retry){
					if(logger.isDebugEnabled()){
						logger.debug("retry for #"+currentRetry);
					}
					currentRetry ++;
				}else{
					if(logger.isInfoEnabled()){
						logger.warn("NoResponse from requested url "+driver.getCurrentUrl());
					}
					break;
				}
			}
		}
		
		if(currentRetry >= maxRetries){
			if(logger.isInfoEnabled()){
				logger.warn("NoResponse from requested url "+driver.getCurrentUrl());
			}
		}
		
		return hasResponse;
	}
	
	
	/**
	 * safely find element without throwing runtime exception
	 * @param driver
	 * @param condition
	 * @return
	 */
	public static WebElement safeFindElement(WebDriver driver, By condition){
		WebElement element = null;
		try{
			element = driver.findElement(condition);
		}catch(Throwable th){
			if(logger.isDebugEnabled()){
				logger.debug("could not locate element due to "+th.getMessage());
			}
		}
		return element;
	}
	
	/**
	 * load page
	 * @param driver
	 * @param pageUrl
	 * @param loadPageTimeOutInSeconds
	 */
	public static void navigate(final WebDriver driver, final String pageUrl, final int numberOfRetries, final long loadPageTimeOutInSeconds){
		Callable<Boolean> navigatorCallable = new Callable<Boolean>() {
			/* 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "WebDriverNavigator";
			}
			
			@Override
			public Boolean call() throws Exception {
				if(driver != null){
					if(logger.isTraceEnabled()){
						logger.trace("navigating to page "+pageUrl);
					}
					
					driver.manage().timeouts().setScriptTimeout(loadPageTimeOutInSeconds, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(loadPageTimeOutInSeconds, TimeUnit.SECONDS);
					
					int retries = numberOfRetries <=0 ? 1 : numberOfRetries;
					while(retries>=1){
						try{
							driver.navigate().to(pageUrl);
							
							break;
						}catch(Exception ex){
							retries --;
							
							if(logger.isInfoEnabled()){
								logger.info("retry to navigate to page "+pageUrl+" #"+retries);
							}
						}
					}
				}
				return true;
			}
		};
		
		Future<Boolean> future = NAVIGATOR_EXECUTOR.submit(navigatorCallable);
		try{
			future.get(MAXIMUM_LOAD_PAGE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
			
			if(logger.isInfoEnabled()){
				logger.info("navigated to page "+driver.getCurrentUrl());
			}
		}catch(Exception ex){
			//get back to the previous page
//			try{
//				driver.navigate().back();
//			}catch(Exception nestE){
//				
//			}
			if(logger.isInfoEnabled()){
				logger.warn("failed to navigate to "+pageUrl+", driver is not responsive for maximum "+MAXIMUM_LOAD_PAGE_TIMEOUT_SECONDS+" seconds, the page may be loaded and is caused by ajax error.");
			}
		}
	}
	
	/**
	 * navigate to the web driver
	 * 
	 * @param driver
	 * @param navigateTo
	 * @param retries
	 */
	public static void navigate(final WebDriver driver, String navigateTo, int retries){
		navigate(driver, navigateTo, retries, DEFAULT_LOAD_PAGE_TIMEOUT_SECONDS);
	}
	
	/**
	 * navigate to the web driver
	 * 
	 * @param driver
	 * @param navigateTo
	 */
	public static void navigate(final WebDriver driver, String navigateTo, long pageLoadTimeout){
		navigate(driver, navigateTo, SeleniumWebNavigator.DEFAULT_NAVIGATION_RETRIES, pageLoadTimeout);
	}
	
	/**
	 * navigate to the web driver
	 * 
	 * @param driver
	 * @param navigateTo
	 */
	public static void navigate(final WebDriver driver, String navigateTo){
		navigate(driver, navigateTo, SeleniumWebNavigator.DEFAULT_NAVIGATION_RETRIES, DEFAULT_LOAD_PAGE_TIMEOUT_SECONDS);
	}
	public static void scrollToBottom(final WebDriver driver){
		((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=100000");
	}

	public static void scollToTop(final WebDriver driver){
		((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=0");
	}
}

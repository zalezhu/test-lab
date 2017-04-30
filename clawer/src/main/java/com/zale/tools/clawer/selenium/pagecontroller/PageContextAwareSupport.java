/**
 * 
 */
package com.zale.tools.clawer.selenium.pagecontroller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * the adaptor class for the PageContextAware implementation
 * @author Zale
 *
 * @param <T>
 */
public class PageContextAwareSupport<T extends PageContext> implements PageContextAware<T>{
	
	private static final Logger logger = Logger.getLogger(PageContextAwareSupport.class);
	
	private Properties properties;
	
	private final T context;
	
	public PageContextAwareSupport(T context){
		if(context == null){
			throw new IllegalArgumentException("PageContext must be set.");
		}
		this.context = context;
		
		loadProperties();
	}
	
	/**
	 * load the properties if defined
	 */
	private void loadProperties(){
		try{
			this.properties = new Properties();
			this.properties.load(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("controller"+File.separator+getClass().getSimpleName()+".properties"), "UTF-8"));
			
			if(logger.isInfoEnabled()){
				logger.info(getClass().getSimpleName()+".properties has been loaded.");
			}
		}catch(Exception ex){
			if(logger.isInfoEnabled()){
				logger.warn("could not load controller properties, default properties will be used for "+getClass().getSimpleName(), ex);
			}
		}
	}
	
	/* 
	 * @see com.infomorrow.data.tools.crawler.PageContextAware#getContext()
	 */
	@Override
	public T getContext() {
		return context;
	}
	
	/**
	 * get the property value, if not exist, null will be returned
	 * @param propertyName
	 * @return
	 */
	public String getProperty(String propertyName){
		return this.properties.getProperty(propertyName);
	}
	
	/**
	 * get the property value if null, default value will be returned
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public String getProperty(String propertyName, String defaultValue){
		String value = this.properties.getProperty(propertyName);
		
		return StringUtils.isNotBlank(value) ? value : defaultValue;
	}
}

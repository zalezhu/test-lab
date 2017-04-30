/**
 * 
 */
package com.zale.tools.clawer.selenium.pagecontroller.pagemodel;



import java.util.HashMap;
import java.util.Map;

/**
 * The model object that will be used by the PageController
 * 
 * @author Zale
 *
 */
public class PageModel {

		private  String pageURL;
		
		private Map<String, String> requestParameterMap;
		
		public PageModel(){
			this.requestParameterMap = new HashMap<String, String>();
		}
		
		public String getPageURL() {
			return pageURL;
		}

		public String getRequestParameter(String requestParameterName) {
			return requestParameterMap.get(requestParameterName);
		}

		public void setRequestParameter(String requestParameterName, String requestParameterValue) {
			this.requestParameterMap.put(requestParameterName, requestParameterValue);
		}
		
}

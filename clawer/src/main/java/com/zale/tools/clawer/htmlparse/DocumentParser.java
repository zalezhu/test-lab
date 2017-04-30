/**
 * 
 */
package com.zale.tools.clawer.htmlparse;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @Author Zale
 * @Date 2017/5/2 下午5:03
 *
 */
public interface DocumentParser<D> {
	
	/**
	 * set the context data if want to pass any infomation to the parser
	 * @return
	 */
	public void setContext(Object contextData);
	
	/**
	 * parse the content from a jdom document
	 * @return
	 */
	public List<D> parse(String document);
	

	/**
	 * the abstract interface for the DocumentParser
	 * @author Zale
	 *
	 * @param <D>
	 */
	public static abstract class AbstractDocumentParser<D> implements DocumentParser<D>{
		
		private Object contextData;
		
		public void setContext(Object contextData){
			this.contextData = contextData;
		}
		
		protected Object getContext(){
			return this.contextData;
		}

		@Override
		public List<D> parse(String document) {
			return parse(Jsoup.parse(document));
		}

		protected abstract List<D> parse(Document parse);
	}
}

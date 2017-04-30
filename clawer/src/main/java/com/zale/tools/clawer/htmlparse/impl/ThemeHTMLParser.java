/**
 * 
 */
package com.zale.tools.clawer.htmlparse.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.zale.tools.clawer.entity.ThemeInfo;
import com.zale.tools.clawer.htmlparse.DocumentParser;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class ThemeHTMLParser extends DocumentParser.AbstractDocumentParser<ThemeInfo> {

	private static final Logger logger = Logger.getLogger(ThemeHTMLParser.class);
	private static final String HTML_CHARSET="utf-8";
	@Override
	public List<ThemeInfo> parse(Document document) {
		List<ThemeInfo> infoList = new ArrayList<ThemeInfo>();
		try {
			Elements elements = document.select("div.fieed-box");
			if(elements.size()>0){
				Element element = elements.first();
				Elements aElements = element.getElementsByTag("a");
				if(aElements.size()>0){
					Date now = new Date();
					infoList.addAll(aElements.stream().map(a->{

						ThemeInfo info = new ThemeInfo();
						info.setHtmlId(a.id());
						info.setCtm(now);
						info.setName(a.ownText());
						return info;
					}).collect(Collectors.toList()));
				}
			}
		} catch (Throwable e) {
			if(logger.isDebugEnabled()){
				logger.debug("failed to parse search HTML.");
			}
		}
		return infoList;
	}


}

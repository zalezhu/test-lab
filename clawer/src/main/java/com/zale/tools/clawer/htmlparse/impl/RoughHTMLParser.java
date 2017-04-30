package com.zale.tools.clawer.htmlparse.impl;

import com.zale.tools.clawer.entity.RoughInfo;
import com.zale.tools.clawer.htmlparse.DocumentParser;
import org.apache.http.conn.routing.RouteInfo;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zale on 2017/4/29.
 */
public final class RoughHTMLParser extends DocumentParser.AbstractDocumentParser<RoughInfo>{
    private String themeId;

    public RoughHTMLParser(String themeId) {
        this.themeId = themeId;
    }

    public static void main(String[] args) {
        System.out.println(DateTime.now().withTimeAtStartOfDay().plus(1493455777).toDate());
    }
    @Override
    protected List<RoughInfo> parse(Document document) {
        Element element = document.getElementById(themeId+"_d");
        Elements elements = element.getElementsByTag("li");
        Date now = new Date();
        List<RoughInfo> routeInfos = elements.stream().map(r ->{
            RoughInfo roughInfo = new RoughInfo();
            roughInfo.setId(r.attr("d"));
            Element imgbox = r.select("div.img-box").first();
            Element txtbox = r.select("div.txt-box").first();
            Element imgboxa = imgbox.getElementsByTag("a").first();
            String forward = imgboxa.attr("href");
            String smallImg = imgboxa.getElementsByTag("img").attr("src");
            roughInfo.setForward(forward);
            roughInfo.setImgURL(smallImg);
            String title = txtbox.select("h3 a").text();
            String content = txtbox.select("p").text();
            roughInfo.setTitle(title);
            roughInfo.setContent(content);
            roughInfo.setCtm(now);
            return roughInfo;
        }).collect(Collectors.toList());
        return routeInfos;
    }
}

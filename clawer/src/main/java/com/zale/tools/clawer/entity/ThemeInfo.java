package com.zale.tools.clawer.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Zale on 2017/5/2.
 */
public class ThemeInfo {
    @Id
    private String htmlId;
    private String name;
    private Date ctm;

    public Date getCtm() {
        return ctm;
    }

    public void setCtm(Date ctm) {
        this.ctm = ctm;
    }

    public String getHtmlId() {
        return htmlId;
    }

    public void setHtmlId(String htmlId) {
        this.htmlId = htmlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ThemeInfo{" + "htmlId='" + htmlId + '\'' + ", name='" + name + '\'' + ", ctm=" + ctm + '}';
    }
}

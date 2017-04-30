package com.zale.tools.clawer.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Zale on 2017/4/29.
 */
public class RoughInfo {
    @Id
    private String id;
    private String imgURL;
    private String localeImg;
    private String forward;
    private String title;
    private String content;
    private Date ctm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getLocaleImg() {
        return localeImg;
    }

    public void setLocaleImg(String localeImg) {
        this.localeImg = localeImg;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCtm() {
        return ctm;
    }

    public void setCtm(Date ctm) {
        this.ctm = ctm;
    }

    @Override
    public String toString() {
        return "RoughInfo{" + "id='" + id + '\'' + ", imgURL='" + imgURL + '\'' + ", localeImg='" + localeImg + '\''
                + ", forward='" + forward + '\'' + ", title='" + title + '\'' + ", content='" + content + '\'' + ", ctm=" + ctm
                + '}';
    }
}

package com.zale.tools.clawer.entity;

import java.io.Serializable;

/**
 * Created by young on 6/11/15.
 */
public class RespEntity<T> implements Serializable{

	private static final long serialVersionUID = 7739979424806069482L;

    private String host;

	private String key;
    private String msg;

    private T ext;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


	public RespEntity() {
        this.key = "";
        this.msg = "";
    }

    public RespEntity(String key) {
        this.key = (key == null ? "" : key);
        this.msg = "";
    }

    public RespEntity(String key, String msg) {
        this.key = (key == null ? "" : key);
        this.msg = (msg == null ? "" : msg);
    }

    public RespEntity(String key, String msg, T ext) {
        this.key = (key == null ? "" : key);
        this.msg = (msg == null ? "" : msg);
        this.ext = ext;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getExt() {
        return ext;
    }

    public void setExt(T ext) {
        this.ext = ext;
    }
    
}

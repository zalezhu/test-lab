package com.zale.tools.task.ping;

/**
 * Created by Zale on 2017/5/2.
 */
public interface Ping {
    boolean ping(String addr);

    boolean resolve(String addr);

}

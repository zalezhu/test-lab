package com.zale.tools.task.ping;

/**
 * Created by Zale on 2017/5/2.
 */
public class UndoPing implements Ping{
    @Override
    public boolean ping(String addr) {
        return false;
    }

    @Override
    public boolean resolve(String addr) {
        return false;
    }
}

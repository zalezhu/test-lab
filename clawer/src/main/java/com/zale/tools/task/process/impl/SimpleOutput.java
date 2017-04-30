package com.zale.tools.task.process.impl;

import com.zale.tools.task.process.OutPut;

/**
 * Created by Zale on 2017/4/29.
 */
public class SimpleOutput<T> implements OutPut {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

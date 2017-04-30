package com.zale.tools.task;

/**
 * Created by Zale on 2017/4/30.
 */
public interface TaskMetadata {
    String getName();

    String getDesc();

    Integer getOrder();

    boolean isSync();
}

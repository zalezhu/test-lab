package com.zale.tools.task;

/**
 * Created by Zale on 2017/4/30.
 */
public abstract class AbstractTaskMetadata implements TaskMetadata{
    private String name;
    private String desc;
    private Integer order;
    private boolean sync;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }



}

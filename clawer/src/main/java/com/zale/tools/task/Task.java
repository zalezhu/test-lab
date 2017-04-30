package com.zale.tools.task;


import com.zale.tools.task.process.TaskProcess;

import java.util.List;

/**
 * Created by Zale on 2017/4/16.
 */
public class Task {
    private String name;
    private String desc;
    private Integer order;
    private boolean sync;
    private List<TaskProcess> processes;


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

    public List<TaskProcess> getProcesses() {
        return processes;
    }

    public void setProcesses(List<TaskProcess> processes) {
        this.processes = processes;
    }
}

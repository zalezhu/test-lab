package com.zale.tools.task;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Zale on 2017/4/16.
 */
public class TaskExecutor {
    private String callbackAddr;
    private String name;
    private String crtTime;
    private int weight;
    private AtomicBoolean running;
    private boolean local;

    public void setRunning(AtomicBoolean running) {
        this.running = running;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public String getCallbackAddr() {
        return callbackAddr;
    }

    public void setCallbackAddr(String callbackAddr) {
        this.callbackAddr = callbackAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean getRunning() {
        return running.get();
    }

    public void setRunning(boolean running) {
        if(this.running == null){
            this.running = new AtomicBoolean(running);
        }else {
            this.running.set(running);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TaskExecutor))
            return false;

        TaskExecutor that = (TaskExecutor) o;

        return callbackAddr != null ? callbackAddr.equals(that.callbackAddr) : that.callbackAddr == null;
    }

    @Override
    public int hashCode() {
        return callbackAddr != null ? callbackAddr.hashCode() : 0;
    }
}

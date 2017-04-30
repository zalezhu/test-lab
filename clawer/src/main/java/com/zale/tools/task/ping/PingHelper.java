package com.zale.tools.task.ping;

import com.zale.tools.task.register.ExecutorRegisterCenter;
import com.zale.tools.task.utils.ExecutorsPool;

/**
 * Created by Zale on 2017/5/1.
 */
public class PingHelper {
    private volatile static PingHelper singleton;
    private PingHelper() {
    }
    public static PingHelper getInstance() {
        if (singleton == null) {
            synchronized (PingHelper.class) {
                if (singleton == null) {
                    singleton = new PingHelper();
                }
            }
        }
        return singleton;
    }

    public void start(){
        ExecutorsPool.FIXED_EXECUTORS.submit(()->{
            while(true) {
                ExecutorRegisterCenter.getTaskExecutors().stream().filter(te -> {
                    Ping ping = PingFactory.getPing(te.getCallbackAddr());
                    return ping.ping(te.getCallbackAddr());
                });
                Thread.sleep(60*1000);
            }
        });
    }
}

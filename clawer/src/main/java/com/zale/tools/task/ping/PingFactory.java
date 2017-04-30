package com.zale.tools.task.ping;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by Zale on 2017/5/2.
 */
public class PingFactory {

    private volatile static PingFactory singleton;
    private static final List<Ping> pingRepo = new ArrayList<>();
    private static UndoPing undoPing= new UndoPing();
    private PingFactory() {
        HttpPing httpPing = new HttpPing();
        pingRepo.add(httpPing);
    }

    public static PingFactory getInstance() {
        if (singleton == null) {
            synchronized (PingFactory.class) {
                if (singleton == null) {
                    singleton = new PingFactory();
                }
            }
        }
        return singleton;
    }

    public static Ping getPing(String address){
        return pingRepo.stream().filter(p->p.resolve(address)).findFirst().orElse(undoPing);
    }

}

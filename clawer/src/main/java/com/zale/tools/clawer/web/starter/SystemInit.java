package com.zale.tools.clawer.web.starter;

import com.zale.tools.clawer.repository.mongo.RoughInfoRepo;
import com.zale.tools.clawer.repository.mongo.ThemeInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Zale on 2017/4/29.
 */
@Component
public class SystemInit {
    @Autowired
    private ThemeInfoRepo themeInfoRepo;
    @Autowired
    private RoughInfoRepo roughInfoRepo;
    
    @PostConstruct
    public void init(){
        initTaskExecutor();
        initTask();
    }

    private void initTask() {
    }

    private void initTaskExecutor() {
    }
}

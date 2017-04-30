package com.zale.tools.clawer.taskprocess;

import java.util.List;
import com.zale.tools.clawer.entity.ThemeInfo;
import com.zale.tools.clawer.htmlparse.impl.ThemeHTMLParser;
import com.zale.tools.clawer.repository.mongo.ThemeInfoRepo;
import com.zale.tools.clawer.selenium.MainPageNavigator;
import com.zale.tools.clawer.utils.SeleniumWebNavigator;
import com.zale.tools.task.process.Input;
import com.zale.tools.task.process.OutPut;
import com.zale.tools.task.process.TaskProcess;
import com.zale.tools.task.process.impl.BoolOutput;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zale on 2017/5/2.
 */
public class ThemeSeleniumTaskProcess implements TaskProcess {
    private Logger logger = LoggerFactory.getLogger(ThemeSeleniumTaskProcess.class);
    private ThemeInfoRepo themeInfoRepo;
    private String url;
    private WebDriver webDriver;

    public ThemeSeleniumTaskProcess(ThemeInfoRepo themeInfoRepo, String url, WebDriver webDriver) {
        this.themeInfoRepo = themeInfoRepo;
        this.url = url;
        this.webDriver = webDriver;
    }


    @Override
    public void process() {
        try {
            new MainPageNavigator(url,webDriver).process((document)->{
                List<ThemeInfo>  themeInfos = new ThemeHTMLParser().parse(document);
                themeInfoRepo.save(themeInfos);
            });
        } catch (SeleniumWebNavigator.PageNotLoadedException e) {
            logger.error(e.getMessage(),e);
        }

    }

}

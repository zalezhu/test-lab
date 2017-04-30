package com.zale.tools.clawer.taskprocess;

import java.util.List;
import com.zale.tools.clawer.entity.RoughInfo;
import com.zale.tools.clawer.htmlparse.impl.RoughHTMLParser;
import com.zale.tools.clawer.repository.mongo.RoughInfoRepo;
import com.zale.tools.clawer.selenium.MainPageNavigator;
import com.zale.tools.clawer.selenium.pagecontroller.RoughPageController;
import com.zale.tools.clawer.selenium.pagecontroller.SeleniumPageContext;
import com.zale.tools.clawer.selenium.pagecontroller.pagemodel.PageModel;
import com.zale.tools.clawer.utils.SeleniumWebNavigator;
import com.zale.tools.task.process.TaskProcess;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zale on 2017/4/29.
 */
public class RoughTaskProcess implements TaskProcess{
    private Logger logger = LoggerFactory.getLogger(RoughTaskProcess.class);
    private String themeId;
    private WebDriver webDriver;
    private String url;
    private RoughInfoRepo roughInfoRepo;

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RoughTaskProcess(String themeId, WebDriver webDriver, String url, RoughInfoRepo roughInfoRepo) {
        this.themeId = themeId;
        this.webDriver = webDriver;
        this.url = url;
        this.roughInfoRepo = roughInfoRepo;
    }

    @Override
    public void process() {
        if(!url.equals(webDriver.getCurrentUrl())){
            try {
                new MainPageNavigator(url,webDriver).process((document)->{
                    PageModel pageModel = new PageModel();
                    pageModel.setRequestParameter("id",themeId);
                    Boolean rst = new RoughPageController(new SeleniumPageContext(webDriver)).action(pageModel);
                    if(rst){
                        List<RoughInfo> roughInfos = new RoughHTMLParser(themeId).parse(webDriver.getPageSource());
                        roughInfoRepo.save(roughInfos);
                    }
                });
            } catch (SeleniumWebNavigator.PageNotLoadedException e) {
                e.printStackTrace();
            }
        }
    }

}

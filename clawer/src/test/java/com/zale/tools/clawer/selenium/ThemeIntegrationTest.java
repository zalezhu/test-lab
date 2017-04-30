/**
 *
 */
package com.zale.tools.clawer.selenium;


import java.util.List;

import com.zale.tools.clawer.entity.RoughInfo;
import com.zale.tools.clawer.entity.ThemeInfo;
import com.zale.tools.clawer.htmlparse.impl.RoughHTMLParser;
import com.zale.tools.clawer.htmlparse.impl.ThemeHTMLParser;
import com.zale.tools.clawer.selenium.pagecontroller.RoughPageController;
import com.zale.tools.clawer.selenium.pagecontroller.SeleniumPageContext;
import com.zale.tools.clawer.selenium.pagecontroller.pagemodel.PageModel;
import com.zale.tools.clawer.utils.SeleniumWebNavigator;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ThemeIntegrationTest {

    @Test
    public void getUThemeFor(){
        System.setProperty("webdriver.chrome.driver" ,  "/Users/Zale/chromedriver");
        WebDriver driver = new ChromeDriver();
//        driver.get("http://www.163.com");

        //create the selenium page context
        SeleniumPageContext pageContext = new SeleniumPageContext(driver);

        //take action on the weibo login controller
        try {
            new MainPageNavigator("http://weixin.sogou.com/", driver).process(inputSource -> {
                List<ThemeInfo> results =
                        new ThemeHTMLParser().parse(inputSource);
                Assert.assertTrue(results.size()>0);
                ThemeInfo result = results.get(0);
                System.out.println(result);
            });
            PageModel pageModel = new PageModel();
            pageModel.setRequestParameter("id","pc_2");
            Assert.assertTrue(new RoughPageController(pageContext).action(pageModel));
            List<RoughInfo> roughInfos = new RoughHTMLParser("pc_2").parse(driver.getPageSource());
            Assert.assertTrue(roughInfos.size()>0);
            RoughInfo result = roughInfos.get(0);
            System.out.println(result);
        } catch (SeleniumWebNavigator.PageNotLoadedException e) {
            e.printStackTrace();
        }
    }
}

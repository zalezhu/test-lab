package com.zale.tools.clawer.selenium;

import com.zale.tools.clawer.constant.ClawerConstants;
import com.zale.tools.clawer.utils.SeleniumWebNavigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

import static com.zale.tools.clawer.utils.SeleniumControllerUtil.safeFindElement;

/**
 * Created by Zale on 2017/5/2.
 */
public class MainPageNavigator extends SeleniumWebNavigator{

    public MainPageNavigator(String url, WebDriver webDriver) {
        super(url, webDriver);
    }

    @Override
    protected Function<WebDriver,?>  getExpectedConditionOnPageLoadedSuccess() {
        return  driver -> {
            WebElement element = safeFindElement(driver,
                    By.className(ClawerConstants.THEME_WEB_LIST_CLASS));
            return element;
        };
    }
}

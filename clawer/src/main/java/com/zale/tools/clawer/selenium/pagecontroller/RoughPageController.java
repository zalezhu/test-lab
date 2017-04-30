package com.zale.tools.clawer.selenium.pagecontroller;

import com.zale.tools.clawer.constant.ClawerConstants;
import com.zale.tools.clawer.selenium.pagecontroller.pagemodel.PageModel;
import com.zale.tools.clawer.utils.SeleniumControllerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import static com.zale.tools.clawer.utils.SeleniumControllerUtil.safeFindElement;

/**
 * Created by Zale on 2017/4/29.
 */
public class RoughPageController extends PageContextAwareSupport<SeleniumPageContext> implements PageController<Boolean,SeleniumPageContext,PageModel> {
    public RoughPageController(SeleniumPageContext context) {
        super(context);
    }

    @Override
    public Boolean action(PageModel model) {
        String themeId = model.getRequestParameter("id");
        SeleniumControllerUtil.safeFindElement(getContext().getWebDriver(), By.id(themeId)).click();
        loadMore(themeId);
        return true;
    }

    private void loadMore(String themeId) {
        WebElement element = SeleniumControllerUtil.safeFindElement(getContext().getWebDriver(), By.className("jzgd"));
        WebElement a =element.findElement(By.tagName("a"));

        while(element.isDisplayed()){
            if(a.isDisplayed()) {
                try {
                    a.click();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            element = SeleniumControllerUtil.safeFindElement(getContext().getWebDriver(), By.className("jzgd"));
            a =element.findElement(By.tagName("a"));
        }
    }

}

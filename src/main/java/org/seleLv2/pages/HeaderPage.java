package org.seleLv2.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.UrlUtils;

import static com.codeborne.selenide.Selenide.refresh;
import static org.seleLv2.elements.Elements.$;


public class HeaderPage {
    private final String link = "//a[@href='%s']";

    public void hoverAndClickComponent(String item) {
        try {
            String departmentComponent = "//*[@id='menu-all-departments-1']//a[contains(text(),'%s')]";
            String locator = String.format(departmentComponent,item);
            String allDepartmentsMenu = "//div[@class='secondary-title']//span[contains(text(),'All departments')]";
            $(allDepartmentsMenu).hover();
            $(locator).scrollTo().click();
        } catch (StaleElementReferenceException e) {
            LogUtils.info(e.getMessage());
        }
    }

    public void selectHeaderMenu(String item){

        String itemLink = String.format(link, UrlUtils.getUrl(item));
        $(itemLink).scrollTo();
        $(itemLink).click();
    }
}

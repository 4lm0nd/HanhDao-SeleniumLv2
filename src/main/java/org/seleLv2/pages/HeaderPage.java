package org.seleLv2.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.UrlUtils;

import static org.seleLv2.elements.Elements.$;


public class HeaderPage {
    private final String allDepartmentsMenu = "//div[@class='secondary-title']//span[contains(text(),'All departments')]";
    private final String departmentComponent = "//*[@id='menu-all-departments-1']//a[contains(text(),'%s')]";
    private final String link = "//a[@href='%s']";
    private final String linkMyAccount = UrlUtils.getUrl("my-account/");
    private final String linkMyCard = UrlUtils.getUrl("cart/");

    public void hoverAndClickComponent(String item) {
        try {
          String locator = String.format(departmentComponent,item);
            $(allDepartmentsMenu).hover();
            $(locator).scrollTo().click();
        } catch (StaleElementReferenceException e) {
            LogUtils.info(e.getMessage());
        }
    }

    public void GotoLoginPage() {
        String authLink =  String.format(link, linkMyAccount);
        $(authLink).click();
    }

    public void GotoShoppingCard() {
        String myCardLink = String.format(link, linkMyCard);
        $(myCardLink).scrollTo();
        $(myCardLink).click();
    }
}

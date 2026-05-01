package org.seleLv2.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.UrlUtils;

import static com.codeborne.selenide.Selenide.refresh;
import static org.seleLv2.elements.Elements.$;


public class HeaderPage {
    private final String link = "//a[@href='%s']";
    private final String linkMyAccount = UrlUtils.getUrl("my-account/");
    private final String linkMyCard = UrlUtils.getUrl("cart/");
    private final String linkShop = UrlUtils.getUrl("shop/");

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

    public void gotoLoginPage() {
        String authLink =  String.format(link, linkMyAccount);
        $(authLink).click();
    }

    public void gotoShoppingCard() {
        String myCardLink = String.format(link, linkMyCard);
        $(myCardLink).scrollTo();
        $(myCardLink).click();
        refresh();
    }

    public void gotoShopPage(){
        String shopLink =  String.format(link, linkShop);
        $(shopLink).click();

    }
}

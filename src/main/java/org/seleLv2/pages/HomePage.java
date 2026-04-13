package org.seleLv2.pages;

import org.seleLv2.drivers.DriverManager;
import org.seleLv2.elements.Elements;
import org.openqa.selenium.By;
import org.seleLv2.utils.UrlUtils;

public class HomePage extends BasePage {
    private final String link = "//a[@href='%s']";
    private final String linkMyAccount = UrlUtils.getUrl("my-account/");
    private final String linkMyCard = UrlUtils.getUrl("cart/");


    public void GotoLoginPage(){
        By authLink =  By.xpath(String.format(link,linkMyAccount));
        Elements.click(authLink);
    }

    public void GotoMyCard(){
        By myCardLink = By.xpath(String.format(link,linkMyCard));
        Elements.scrollToElement(DriverManager.getDriver().findElement(myCardLink));
        Elements.click(myCardLink);
    }

}

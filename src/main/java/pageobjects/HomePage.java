package pageobjects;

import elements.Elements;
import org.openqa.selenium.By;

public class HomePage {
    private final By authLink = By.xpath("//div[@class='header-top']//a[contains(@href,'my-account')]");

    public void GotoLoginPage(){
        Elements.click(authLink);
    }
}

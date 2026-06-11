package org.seleLv2.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.seleLv2.drivers.DriverManager;
import java.util.List;

public class Elements {

    private final By locator;

    public Elements(String xpath) {
        this.locator = By.xpath(xpath);
    }

    public static Elements $$(String xpath) {
        return new Elements(xpath);
    }

    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    public List<WebElement> gets() {
        return driver().findElements(locator);
    }

    public WebElement get(int index) {
        return gets().get(index);
    }

    public int size() {
        return gets().size();
    }

    public WebElement first() {
        return gets().get(0);
    }

    public WebElement last() {
        List<WebElement> elements = gets();
        return elements.get(elements.size() - 1);
    }
}

package org.seleLv2.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleLv2.utils.WaitUtils;


public class Textbox extends Elements {

    public static void sendKeys(By locator, String text) {
        WebElement element = WaitUtils.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
}

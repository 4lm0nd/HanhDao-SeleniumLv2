package org.seleLv2.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.seleLv2.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleLv2.utils.WaitUtils;
import java.util.List;


public class Elements {
    public static WebElement find(By locator) {
        return DriverManager.getDriver().findElement(locator);
    }

    public static void click(By locator) {
        WaitUtils.waitForClickable(locator);
        DriverManager.getDriver().findElement(locator).click();
    }

    public static void hover(By locator){
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(DriverManager.getDriver().findElement(locator)).perform();
    }

    public static String getText(By locator) {
        return WaitUtils.waitForVisible(locator).getText();
    }

    public static List<WebElement> getElements(By locator) {
        return WaitUtils.waitForAllVisible(locator);
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }
}

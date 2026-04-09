package elements;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Elements {
    public static WebElement find(By locator) {
        return DriverManager.getDriver().findElement(locator);
    }
    public static void click(By locator) {
        find(locator).click();
    }
}

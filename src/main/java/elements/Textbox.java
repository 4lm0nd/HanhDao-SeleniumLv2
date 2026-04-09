package elements;

import org.openqa.selenium.By;



public class Textbox extends Elements {

    public static void sendKeys(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }
}

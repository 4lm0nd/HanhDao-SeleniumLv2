package org.seleLv2.utils;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class WaitUtils {
    public static WebElement waitForVisible(By locator)
         {
            return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constant.timeout))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        public static List<WebElement> waitForAllVisible(By locator) {
            return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constant.timeout))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        }

        public static void waitForClickable(By locator) {
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constant.timeout))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        }
    }


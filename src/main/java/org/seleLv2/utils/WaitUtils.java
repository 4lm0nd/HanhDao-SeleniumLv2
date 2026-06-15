package org.seleLv2.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.drivers.DriverManager;

import java.time.Duration;
import java.util.function.BooleanSupplier;


public class WaitUtils {

    public static void waitForPageLoad(int timeoutInSeconds) {
        WebDriver driver = DriverManager.getDriver();

        System.out.println("Driver = " + driver);

        if (driver instanceof RemoteWebDriver) {
            System.out.println(
                    "Session = "
                            + ((RemoteWebDriver) driver).getSessionId()
            );

            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(timeoutInSeconds)
            );

            wait.until(d ->
                    ((JavascriptExecutor) d)
                            .executeScript("return document.readyState")
                            .equals("complete"));
        }
    }

    public static void waitForProcessing (String url){
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constant.timeInSecond))
                .until(ExpectedConditions.urlContains(url));
    }

    public static void waitForStaleness (WebElement locator){
        new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(Constant.timeInSecond))
                .until(ExpectedConditions.stalenessOf(locator));
    }

    public static void waitForElementUpdate(){
        new WebDriverWait( DriverManager.getDriver(), Duration.ofSeconds(Constant.timeInSecond))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(".blockUI.blockOverlay")
                ));
    }


    public static boolean retryWait(
            BooleanSupplier condition,
            int timeoutSeconds,
            int pollingMillis){

        long endTime = System.currentTimeMillis()
                + timeoutSeconds * 1000L;

        while (System.currentTimeMillis() < endTime) {

            try {

                if (condition.getAsBoolean()) {
                    return true;
                }

            } catch (Exception ignored) {
            }

            sleep(pollingMillis);
        }

        return false;
    }

    public static void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

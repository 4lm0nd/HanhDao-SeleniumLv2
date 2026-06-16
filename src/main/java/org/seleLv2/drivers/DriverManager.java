package org.seleLv2.drivers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        if (driver.get() == null) {
            WebDriver driverInstance = BrowserFactory.createDriver();
            driver.set(driverInstance);
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver Not Started Yet.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static void acceptAlert() {
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.accept();
    }

    public static void rejectAlert() {
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.dismiss();
    }

    public static void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
    }

    public static void setBrowserSize() {
        String browserSize = ConfigManager.get("browserSize");
        String[] size = browserSize.split("x");
        DriverManager.getDriver().manage().window().setSize(
                new Dimension(
                        Integer.parseInt(size[0]),
                        Integer.parseInt(size[1]))
        );
    }
}

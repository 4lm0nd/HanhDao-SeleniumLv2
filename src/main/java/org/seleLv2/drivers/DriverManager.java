package org.seleLv2.drivers;

import org.seleLv2.common.enums.Browsers;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        if (driver.get() == null) {
            WebDriver driverInstance = DriverFactory.createDriver(Browsers.valueOf(browser.toUpperCase()));
            driverInstance.manage().window().maximize();
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
}

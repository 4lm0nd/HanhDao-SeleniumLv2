package org.seleLv2.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class BrowserFactory {

    public static WebDriver createDriver() {

        String browser = ConfigManager.get("browser");


        switch (browser.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options =
                        new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                return new ChromeDriver(options);

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(new FirefoxOptions());

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(new EdgeOptions());

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}

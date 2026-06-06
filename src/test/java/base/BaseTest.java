package base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.seleLv2.drivers.ConfigManager;
import org.seleLv2.drivers.DriverManager;
import org.seleLv2.listeners.MyCustomListener;
import org.seleLv2.utils.CookieUtils;
import org.seleLv2.utils.LogUtils;
import org.testng.annotations.*;
import java.time.Duration;


public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(2));

        String browserSize = ConfigManager.get("browserSize");

        String[] size = browserSize.split("x");

        driver.manage().window().setSize(
                new Dimension(
                        Integer.parseInt(size[0]),
                        Integer.parseInt(size[1]))
        );

        driver.get(ConfigManager.get("url"));
        CookieUtils.acceptCookiesIfPresent();
    }

    @AfterMethod
    public void tearDown() {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
        }
    }
}





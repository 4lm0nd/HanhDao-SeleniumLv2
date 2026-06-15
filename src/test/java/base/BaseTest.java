package base;

import org.openqa.selenium.WebDriver;
import org.seleLv2.drivers.ConfigManager;
import org.seleLv2.drivers.DriverManager;
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
        DriverManager.setBrowserSize();
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





package base;

import common.constant.Constant;
import drivers.DriverManager;
import org.testng.annotations.*;
import listeners.TestListener;
import static drivers.DriverManager.quitDriver;

@Listeners({listeners.TestListener.class})
public class BaseTest {
        @BeforeMethod
        @Parameters("browser")
        public void setUp(@Optional("chrome") String browser) {
            DriverManager.initDriver(browser);
            TestListener.setDriver(DriverManager.getDriver());
            DriverManager.getDriver().get(Constant.url);
        }
      @AfterMethod
       public void tearDown() {
            quitDriver();
      }
  }


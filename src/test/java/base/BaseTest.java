package base;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.drivers.DriverManager;
import org.testng.annotations.*;
import static org.seleLv2.drivers.DriverManager.quitDriver;

public class BaseTest {
    @BeforeMethod
           public void setUp() {
            DriverManager.initDriver(Constant.browser);
            DriverManager.getDriver().get(Constant.url);
        }
    @AfterMethod
     public void tearDown() {quitDriver();}
  }


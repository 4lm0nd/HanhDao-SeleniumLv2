package base;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Selenide;
import org.seleLv2.drivers.DriverConfig;
import org.seleLv2.utils.LogUtils;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverConfig.init();
        open("/");
        LogUtils.info("=== TEST START ===");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
        LogUtils.info("=== TEST END ===");
    }
}


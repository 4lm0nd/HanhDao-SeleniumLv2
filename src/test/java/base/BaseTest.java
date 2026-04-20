package base;

import static com.codeborne.selenide.Selenide.open;
import org.seleLv2.drivers.DriverConfig;
import org.seleLv2.pages.CartPage;
import org.seleLv2.pages.HeaderPage;
import org.seleLv2.pages.MyAccountPage;
import org.seleLv2.pages.ProductPage;
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
//        Selenide.closeWebDriver();
//        LogUtils.info("=== TEST END ===");
    }
}


package org.seleLv2.drivers;
import com.codeborne.selenide.Configuration;
import org.seleLv2.common.enums.Browsers;
import org.seleLv2.utils.LogUtils;

import static org.seleLv2.common.enums.Browsers.EDGE;

public class DriverConfig {

    public static void init() {

        Configuration.headless = ConfigManager.getBoolean("headless");
        Configuration.timeout = ConfigManager.getLong("timeOut");
        Configuration.baseUrl = ConfigManager.get("url");
        Configuration.browserSize = ConfigManager.get("browserSize");
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        String browserStr = ConfigManager.get("browser");

        Browsers browserType = Browsers.fromString(browserStr);
        switch (browserType) {
            case CHROME:
                Configuration.browser = "chrome";
                break;
            case FIREFOX:
                Configuration.browser = "firefox";
                break;
            case EDGE:
                Configuration.browser = "edge";
                break;
            default:
                Configuration.browser = "chrome";
        }

        LogUtils.info("=== SELENIDE CONFIG ===");
        LogUtils.info("Env: " + System.getProperty("env", "dev"));
        LogUtils.info("Browser: " + Configuration.browser);
        LogUtils.info("BaseUrl: " + Configuration.baseUrl);
    }

}

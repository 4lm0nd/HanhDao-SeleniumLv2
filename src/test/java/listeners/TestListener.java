package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        webDriver = driver;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            String testName = result.getMethod().getMethodName();
            ScreenshotUtils.takeScreenshot(driver, testName);
        }
    }
}

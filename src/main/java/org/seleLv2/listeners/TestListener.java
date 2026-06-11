package org.seleLv2.listeners;


import org.openqa.selenium.WebDriver;
import org.seleLv2.drivers.DriverManager;
import org.seleLv2.utils.HtmlCaptureUtils;
import org.seleLv2.utils.LogUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.seleLv2.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

            try {

                WebDriver driver = DriverManager.getDriver();
                String testName = result.getMethod().getMethodName();

                try {
                    ScreenshotUtils.takeScreenshot(driver, testName);
                } catch (Exception e) {
                    LogUtils.info("Failed to capture screenshot", e);
                }

                try {
                    String htmlPath = HtmlCaptureUtils.save(testName);
                    LogUtils.info("Captured HTML: " + htmlPath);
                } catch (Exception e) {
                    LogUtils.info("Failed to capture HTML", e);
                }

            } catch (Exception e) {
                LogUtils.info("Error in onTestFailure", e);
            }
        }
}
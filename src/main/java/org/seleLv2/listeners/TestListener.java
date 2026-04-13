package org.seleLv2.listeners;

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
            if (DriverManager.getDriver() != null) {
                String testName = result.getMethod().getMethodName();
                ScreenshotUtils.takeScreenshot(testName);
                HtmlCaptureUtils.save(testName);
            }
        } catch (Exception e) {
           LogUtils.info("Error in onTestFailure: " + e.getMessage());
        }
    }
}

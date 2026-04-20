package org.seleLv2.listeners;

import com.codeborne.selenide.WebDriverRunner;
import org.seleLv2.utils.HtmlCaptureUtils;
import org.seleLv2.utils.LogUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.seleLv2.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            String testName = result.getMethod().getMethodName();

            // check driver đã start chưa
            if (!WebDriverRunner.hasWebDriverStarted()) {
                LogUtils.info("Driver not started - skip capture");
                return;
            }

            // screenshot
            String screenshotPath = ScreenshotUtils.takeScreenshot(testName);

            // html
            String htmlPath = HtmlCaptureUtils.save(testName);

            LogUtils.info("Captured screenshot: " + screenshotPath);
            LogUtils.info("Captured HTML: " + htmlPath);

        } catch (Exception e) {
            LogUtils.info("Error in onTestFailure", e);
        }
    }
}